package junit3.part4;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class FileReaderTester extends TestCase {

    public static final String DATA_TXT_FILE_PATH = "/Users/leeho/workspace/refactoring/src/test/java/junit3/part4/data.txt";
    private FileReader input;

    public FileReaderTester(String name) {
        super(name);
    }

    protected void setUp(){
        try {
            input = new FileReader(DATA_TXT_FILE_PATH);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException("테스트 파일을 열 수 없음");
        }
    }

    protected void tearDown(){
        try {
            input.close();
        } catch (IOException e) {
            throw new RuntimeException("테스트 파일을 닫는 중 에러 발생");
        }
    }

    public void testReadSuccessWhenUseAssert() throws IOException {
        char ch = '&';
        for (int i = 0; i < 4; i++) {
            ch = (char) input.read();
        }
        assert('d' == ch);
    }

    public void testReadFailWhenUseAssert() throws IOException {
        char ch = '&';
        for (int i = 0; i < 4; i++) {
            ch = (char) input.read();
        }
        assert('ㅡ' == ch);
    }

    public void testReadSuccessWhenUseAssertEquals() throws IOException {
        char ch = '&';
        for (int i = 0; i < 4; i++) {
            ch = (char) input.read();
        }
        assertEquals('d', ch);
    }

    public void testReadFailWhenUseAssertEquals() throws IOException {
        char ch = '&';
        for (int i = 0; i < 4; i++) {
            ch = (char) input.read();
        }
        assertEquals('m', ch);
    }


    public static Test suite(){
        TestSuite suite = new TestSuite();
//        suite.addTest(new FileReaderTester("testReadSuccessWhenUseAssert"));
        suite.addTest(new FileReaderTester("testReadFailWhenUseAssert"));
//        suite.addTest(new FileReaderTester("testReadSuccessWhenUseAssertEquals"));
//        suite.addTest(new FileReaderTester("testReadFailWhenUseAssertEquals"));
        return suite;
    }

    public static void main(String[] args) {
        junit.textui.TestRunner.run(suite());
    }
}
