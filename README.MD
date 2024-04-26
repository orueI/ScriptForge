# ScriptForge

ScriptForge is a Kotlin multiplatform library designed to simplify the creation of programming languages. With
ScriptForge, developers can quickly build necessary language components using its base compiler and engine classes.

## Features

- **Statically Typed Language Opportunities:** ScriptForge provides support for statically typed languages, offering
  developers the flexibility to define types and enforce type safety.
- **Dynamic Load Libraries:** Developers can dynamically load libraries into their scripts, enhancing extensibility and
  modularity.
- **Integration with External APIs:** ScriptForge seamlessly integrates with external APIs, allowing developers to
  leverage external functionality within their scripts.
- **Execution Control and Injection Protection:** ScriptForge offers full control over executing code, along with
  protection against injections to ensure script security.

## Getting Started

To start using ScriptForge in your project, follow these steps:

1. Add the ScriptForge dependency to your project.
2. Create a `Runner` instance, either by using the default `ScriptForgeRunner` constructor or by customizing it
   according to your requirements.
3. Invoke the `runCode` method on the `Runner` instance, passing the code as an argument.

```kotlin
val runner = ScriptForgeRunner()

runner.runCode("your code")
```

## Creating Custom Libraries

Developers can create custom libraries for ScriptForge using the following approaches:

### Pair Declarations

Define a list of `Pair` declarations in regex format, with values or functions. This list serves as the custom library.

### Uploading Libraries

Libraries can be uploaded in two ways:

1. **In the constructor argument of `ScriptForgeScriptEngine`:** Provide a static library.
2. **Using the `loadLib` method in `Runner` for dynamic loading:** This method can be reset via `resetLibs`.

### Customizing Syntax

Advanced users can customize syntax by following these steps:

1. Extend the `TokenTypesProvider` class to define a declarative sequence for finding tokens and adding new tokens.
2. Optionally, extend `AstEngine` or `ScriptForgeScriptEngine` to use or extend existing syntax.
3. Create a `Runner` instance with the customized classes.
