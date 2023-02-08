# Docs
**MicroJava** is a *high-level programming language*, *similar*, but *simpler* than **Java**.
Similarly to Java, MicroJava source files are compiled to **bytecode**, which is then executed by a **virtual machine** (**MicroJava Virtual Machine**). MicroJava VM is a simple *interpretative emulator*.

Full project specification can be found [here](https://github.com/stefcon/microjava-compiler/blob/master/CompilerSpecification.pdf), while MicroJava language can be found [here](https://github.com/stefcon/microjava-compiler/blob/master/MicroJavaSpecification.pdf)

## Commands for generating code
Position inside src folder inside project
- Generating lexer:
##### java -cp ../lib/JFlex.jar JFlex.Main -d rs\ac\bg\etf\pp1 ..\spec\mjlexer.flex >output.out 2>output.err
 - Generating parser:
##### java -cp ../lib/cup_v10k.jar java_cup.Main -destdir rs\ac\bg\etf\pp1 -parser MJParser -ast rs.ac.bg.etf.pp1.ast -buildtree ..\spec\mjparser.cup >output.out 2>output.err
 - Compiling and running the code:
##### test\program.mj test\program.obj (local file Compiler.java)
 - Running with debug option:
##### -debug test\program.obj (lib mj-runtime-1.1.jar class Run.class)
 - Disassmbler:
##### test\program.obj (lib mj-runtime-1.1.jar lib disasm.class
