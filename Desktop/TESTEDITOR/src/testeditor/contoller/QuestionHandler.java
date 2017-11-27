package testeditor.contoller;


import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;


public class QuestionHandler extends DefaultHandler {
    private List<Question> questionsList;
    private Question question;
    private List<Answer> answers;
    private Answer answer;

    QuestionHandler() {
        questionsList = new ArrayList<>();
        question = null;
        answer = null;
        answers = new ArrayList<>();
    }

    List<Question> getQuestions() {
        return questionsList;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes)
            throws SAXException {
        if (qName.equalsIgnoreCase(XMLFile.XMLConst.QUESTION)) {
            String QName = attributes.getValue(XMLFile.XMLConst.TEXT);
            String QText = attributes.getValue(XMLFile.XMLConst.NAME);
            question = new MultiChoice(QName, QText);
        } else if (qName.equalsIgnoreCase(XMLFile.XMLConst.ANSWER)) {
            String aText = attributes.getValue(XMLFile.XMLConst.TEXT);
            int fraction = Integer.parseInt(attributes.getValue(XMLFile.XMLConst.FRACTION));
            answer = new Answer(aText, fraction);
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (qName.equalsIgnoreCase(XMLFile.XMLConst.QUESTION)) {
            question.setAnswers(answers);
            questionsList.add(question);
            answers = new ArrayList<>();
        } else if (qName.equalsIgnoreCase(XMLFile.XMLConst.ANSWER)) {
            answers.add(answer);


        }
    }

    @Override
    public void characters(char ch[], int start, int length) throws SAXException {

    }
}
