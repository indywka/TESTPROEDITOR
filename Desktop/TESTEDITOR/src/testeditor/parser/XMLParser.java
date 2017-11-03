package testeditor.parser;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import testeditor.Test;
import testeditor.question.Answer;
import testeditor.question.MultiChoice;
import testeditor.question.Question;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Парсер для чтения файлов в формате XML
 */
public class XMLParser extends Parser {

    public Test getTest(String filepath) throws IOException {
        Document doc;
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            doc = builder.parse(filepath);
        } catch (Exception e) {
            throw new IOException("Ошибка открытия XML документа");
        }

        Test test = Test.createTest();
        Question q;
        int questionsCount = doc.getDocumentElement().getElementsByTagName("question").getLength();
        NodeList questionNodes = doc.getDocumentElement().getElementsByTagName("question");

        for (int i = 0; i < questionsCount; i++) {
            Element questionElement = (Element) questionNodes.item(i);
            String questionType = questionElement.getAttribute("type");

            switch (questionType) {
                case "multichoice":
                    q = parseMultiChoice(questionElement);
                    test.add(q);

                    break;
                    }
        }

        return test;
    }


    private MultiChoice parseMultiChoice(Element questionElement) {
        String name = getTextField("name", questionElement);
        String head = getTextField("questiontext", questionElement);

        ArrayList<Answer> answerList = parseAnswerList(questionElement);

        MultiChoice q = new MultiChoice(name, head, answerList);
        q = (MultiChoice) prepareQuestion(q, questionElement);

        return q;
    }

    private Question prepareQuestion(Question q, Element questionElement) {
        float defaultGrade = Float.parseFloat(getField("defaultgrade", questionElement));
        float penalty = Float.parseFloat(getField("penalty", questionElement));

        q.setDefaultGrade(defaultGrade);
        q.setPenalty(penalty);

        return q;
    }

    private ArrayList<Answer> parseAnswerList(Element questionElement) {
        ArrayList<Answer> answerList = new ArrayList<>();
        NodeList answerElements = questionElement.getElementsByTagName("answer");

        String correctFeedback = getTextField("correctfeedback", questionElement);
        String partiallyCorrectFeedback = getTextField("partiallycorrectfeedback", questionElement);
        String incorrectFeedback = getTextField("incorrectfeedback", questionElement);

        for (int i = 0; i < answerElements.getLength(); i++) {
            Element answerElement = (Element) answerElements.item(i);

            String text = answerElement.getElementsByTagName("text").item(0).getTextContent();
            int fraction = Integer.parseInt(answerElement.getAttribute("fraction"));

            String comment;

            if (fraction == Answer.MIN_DEGREE) comment = incorrectFeedback;
            else if (fraction == Answer.MAX_DEGREE) comment = correctFeedback;
            else comment = partiallyCorrectFeedback;

            answerList.add(new Answer(text, fraction, comment));
        }

        return answerList;
    }

    private String getTextField(String elementName, Element questionElement) {
        Element fieldElement = (Element) questionElement.getElementsByTagName(elementName).item(0);
        if (fieldElement == null) return "";

        Node textElement = fieldElement.getElementsByTagName("text").item(0);
        if (textElement == null) return "";

        return textElement.getTextContent().replaceAll("<[a-zA-Z\\s/+>]", "");
    }

    private String getField(String elementName, Element questionElement) {
        Element fieldElement = (Element) questionElement.getElementsByTagName(elementName).item(0);
        if (fieldElement == null) return "";

        return fieldElement.getTextContent().replaceAll("<[a-zA-Z\\s/+>]", "");
    }
}
