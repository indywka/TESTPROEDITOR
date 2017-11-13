package testeditor.xml;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;
import testeditor.Test;
import testeditor.question.Answer;
import testeditor.question.Question;

import javax.swing.*;
import javax.xml.parsers.*;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Properties;


public class XMLFile {
    private DefaultListModel<Question> listModel;
    private String filepath;

    public XMLFile(String filepath) {
        this.filepath = filepath;
    }

    public XMLFile(String filepath, DefaultListModel<Question> listModel) {
        this.filepath = filepath;
        this.listModel = listModel;
    }

    public void save() throws TransformerException, ParserConfigurationException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.newDocument();

        Element rootElement = doc.createElement(XMLConst.QUESTIONS);
        doc.appendChild(rootElement);

        for (Question q : Test.getTest()) {
            Element questElement = doc.createElement(XMLConst.QUESTION);
            rootElement.appendChild(questElement);

            String string = q.getQName();
            questElement.setAttribute(XMLConst.TEXT, string);
            string = q.getQText();
            questElement.setAttribute(XMLConst.NAME, string);

            List<Answer> answerList = q.getAnswerList();

            for (Answer anAnswerList : answerList) {
                Element AnswerElement = doc.createElement(XMLConst.ANSWER);
                questElement.appendChild(AnswerElement);

                string = String.valueOf(anAnswerList.getDegree());
                AnswerElement.setAttribute(XMLConst.FRACTION, string);

                string = anAnswerList.getAText();
                AnswerElement.setAttribute(XMLConst.TEXT, string);
                questElement.appendChild(AnswerElement);

                TransformerFactory transformerFactory = TransformerFactory.newInstance();
                Transformer transformer = transformerFactory.newTransformer();
                Properties outFormat = new Properties();
                outFormat.setProperty(OutputKeys.INDENT, "yes");
                transformer.setOutputProperties(outFormat);
                DOMSource source = new DOMSource(doc);
                StreamResult result = new StreamResult(
                        new File(filepath));
                transformer.transform(source, result);
            }
        }

    }

    public void read() {
        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
        try {
            SAXParser saxParser = saxParserFactory.newSAXParser();
            QuestionHandler studentHandler = new QuestionHandler();
            saxParser.parse(new File(filepath), studentHandler);

            List<Question> questions = studentHandler.getQuestions();

            listModel.clear();
            for (Question question : questions) {
                listModel.addElement(question);
            }

        } catch (SAXException | ParserConfigurationException | IOException e) {
            e.printStackTrace();
        }
    }

    class XMLConst {
        static final String QUESTIONS = "questions";
        static final String TEXT = "text";
        static final String NAME = "name";
        static final String ANSWER = "answer";
        static final String FRACTION = "fraction";
        static final String QUESTION = "question";


    }
}
