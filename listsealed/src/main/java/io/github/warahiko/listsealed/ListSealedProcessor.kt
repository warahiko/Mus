package io.github.warahiko.listsealed

import com.google.devtools.ksp.processing.CodeGenerator
import com.google.devtools.ksp.processing.Dependencies
import com.google.devtools.ksp.processing.KSPLogger
import com.google.devtools.ksp.processing.Resolver
import com.google.devtools.ksp.processing.SymbolProcessor
import com.google.devtools.ksp.symbol.ClassKind
import com.google.devtools.ksp.symbol.KSAnnotated
import com.google.devtools.ksp.symbol.KSClassDeclaration
import com.google.devtools.ksp.symbol.Modifier
import com.google.devtools.ksp.validate

class ListSealedProcessor(
    private val codeGenerator: CodeGenerator,
    private val logger: KSPLogger,
) : SymbolProcessor {

    override fun process(resolver: Resolver): List<KSAnnotated> {
        val symbols = resolver
            .getSymbolsWithAnnotation("io.github.warahiko.listsealed.ListSealed")
            .filterIsInstance<KSClassDeclaration>()

        val (processable, next) = symbols.partition { it.validate() }

        processable.forEach { symbol ->
            val className = (symbol.qualifiedName ?: symbol.simpleName).asString()
            if (Modifier.SEALED !in symbol.modifiers) {
                logger.error(
                    "Class $className is not a sealed class/interface.",
                    symbol = symbol,
                )
                return@forEach
            }
            if (symbol.declarations.all { (it as? KSClassDeclaration)?.isCompanionObject != true }) {
                logger.error(
                    "Class $className does not have a companion object.",
                    symbol = symbol,
                )
                return@forEach
            }

            generateList(symbol)
        }

        return next
    }

    private fun generateList(classDeclaration: KSClassDeclaration) {
        val packageName = classDeclaration.packageName.asString()
        val qualifiedClassName = classDeclaration.qualifiedName?.asString() ?: return
        val className = classDeclaration.simpleName.asString()
        codeGenerator.createNewFile(
            Dependencies(aggregating = false),
            packageName,
            "${className}ListSealed",
        ).use { stream ->
            val code = buildString {
                appendLine(
                    """
                package $packageName

                val $qualifiedClassName.Companion.objects: List<$qualifiedClassName>
                    """.trimIndent()
                )

                val sealedSubclasses = classDeclaration.getSealedSubclasses()
                    .filter { it.classKind == ClassKind.OBJECT }
                if (sealedSubclasses.none()) {
                    appendLine("${indent}get() = emptyList()")
                    return@buildString
                }

                appendLine("${indent}get() = listOf(")
                sealedSubclasses
                    .forEach { subclass ->
                        val qualifiedSubclassName = subclass.qualifiedName?.asString() ?: return@forEach
                        appendLine("$indent$indent$qualifiedSubclassName,")
                    }
                appendLine("$indent)")
            }

            stream.write(code.toByteArray())
        }
    }

    companion object {
        private const val indent = "    "
    }
}
