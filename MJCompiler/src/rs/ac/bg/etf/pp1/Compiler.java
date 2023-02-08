package rs.ac.bg.etf.pp1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

import java_cup.runtime.Symbol;
import rs.ac.bg.etf.pp1.ast.Program;
import rs.ac.bg.etf.pp1.ast.SyntaxNode;
import rs.ac.bg.etf.pp1.util.Log4JUtils;
import rs.etf.pp1.mj.runtime.Code;
import rs.etf.pp1.mj.runtime.Run;
import rs.etf.pp1.mj.runtime.disasm;
import rs.etf.pp1.symboltable.Tab;

public class Compiler {

	static {
		DOMConfigurator.configure(Log4JUtils.instance().findLoggerConfigFile());
		Log4JUtils.instance().prepareLogFile(Logger.getRootLogger());
	}
	
	public static void main(String[] args) throws Exception {
		Logger log = Logger.getLogger(Compiler.class);
		File sourceCode;
		if (args.length >= 2) {
			sourceCode = new File(args[0]);
		}
		else if (args.length == 0) {
			sourceCode = new File("program1.mj");
		}
		else {
			log.error("Not enough arguments supplied! Usage: MJParser <source-file> <obj-file> or no arguments at all!");
			return;
		}
		
		
		if (!sourceCode.exists()) {
			log.error("Source file [" + sourceCode.getAbsolutePath() + "] not found!");
			return;
		}
			
		log.info("Compiling source file: " + sourceCode.getAbsolutePath());
		
		try (BufferedReader br = new BufferedReader(new FileReader(sourceCode))) {
			Yylex lexer = new Yylex(br);
			MJParser p = new MJParser(lexer);
			SemanticAnalyzer semanticCheck = null;
	        Symbol s = p.parse();
	        Program prog = null;
	        
	        if (!p.errorDetected) {	        	
	        	prog = (Program)(s.value);
	        	
	        	Tab.init(); // Universe scope
	        	semanticCheck = new SemanticAnalyzer();
	        	prog.traverseBottomUp(semanticCheck);
	        	
	        	Tab.dump();
	        	
	        	log.info("===============================");
	        	log.info(prog.toString(""));
	        	log.info("===============================");
	        }
	        
	        if (!p.errorDetected && semanticCheck.passed()) {
	        	log.info("Parsiranje uspesno zavrseno!");
	        	
	        	File objFile;
	        	String objFilePath;
	        	if (args.length > 0) objFilePath = args[1];
	        	else objFilePath = "test\\program.obj";
	        	
	        	objFile = new File(objFilePath);
	        	log.info("Generating bytecode file: " + objFile.getAbsolutePath());
	        	if (objFile.exists())
	        		objFile.delete();
	        	
	        	CodeGenerator codeGenerator = new CodeGenerator(
	        			semanticCheck.getClassList(),
	        			semanticCheck.getGlobalFunctions(),
	        			semanticCheck.getClassConstructorsMap());
	        	prog.traverseBottomUp(codeGenerator);
	        	Code.mainPc = codeGenerator.getMainPc();
	        	Code.write(new FileOutputStream(objFile));
	        	log.info("Parsiranje uspesno zavrseno!");
	        	
	        	log.info("Izvrsvanje programa...");
	        	
	        	disasm.main(new String[] {objFilePath});
	        	Run.main(new String[] {objFilePath});
	        }
	        else {
	        	log.error("Parsiranje NIJE uspesno zavrseno!");
	        }
		}
	}
}
