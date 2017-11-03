package testeditor.saver;

import org.w3c.dom.CDATASection;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import testeditor.question.Answer;
import testeditor.question.Question;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.StringWriter;
import java.util.List;


public class XmlSaver extends Saver {
    private Document doc;

    public XmlSaver(String filepath) throws Exception {
        super(filepath);

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        doc = builder.newDocument();
    }

    @Override
    public String doLineForMultiChoice(Question q) {
        Element questionElement = doc.createElement("question");
        questionElement.setAttribute("type", "multichoice");

        questionElement = setTextField(questionElement, q.getQName());
        questionElement = setTextFieldWithCData(questionElement, q.getQHead());

        List<Answer> answerList = q.getAnswerList();

        for (Answer anAnswerList : answerList) {
            Element AnswerElement = doc.createElement("answer");
            AnswerElement.setAttribute("fraction", String.valueOf(anAnswerList.getDegree()));

            Element AnswerTextElement = doc.createElement("text");
            CDATASection AnswerTextSection = doc.createCDATASection(String.valueOf(anAnswerList.getDegree()));
            AnswerTextElement.appendChild(AnswerTextSection);

            AnswerElement.appendChild(AnswerTextElement);
            questionElement.appendChild(AnswerElement);
        }

        return xmlToString(questionElement);
    }

    @Override
    public String doLineForShortAnswer(Question q) {
        Element questionElement = doc.createElement("question");
        questionElement.setAttribute("type", "shortanswer");

        questionElement = setTextField(questionElement, q.getQName());
        questionElement = setTextFieldWithCData(questionElement, q.getQHead());

        List<Answer> answerList = q.getAnswerList();

        for (Answer anAnswerList : answerList) {
            Element AnswerElement = doc.createElement("answer");
            AnswerElement.setAttribute("fraction", String.valueOf(anAnswerList.getDegree()));

            Element AnswerTextElement = doc.createElement("text");
            AnswerTextElement.setNodeValue(String.valueOf(anAnswerList.getDegree()));

            AnswerElement.appendChild(AnswerTextElement);
            questionElement.appendChild(AnswerElement);
        }

        return xmlToString(questionElement);
    }


    private String xmlToString(Element questionElement) {
        try {
            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer tr = tf.newTransformer();
            //tr.setOutputProperty(OutputKeys.INDENT, "yes");
            StringWriter wr = new StringWriter();
            tr.transform(new DOMSource(questionElement), new StreamResult(wr));

            return wr.getBuffer().toString();
        } catch (TransformerException e2) {
            return "";
        }
    }

    private Element setTextField(Element questionElement,
                                 String value) {
        Element NameElement = doc.createElement("name");
        Element NameTextElement = doc.createElement("text");
        NameTextElement.setTextContent(value);
        NameElement.appendChild(NameTextElement);
        questionElement.appendChild(NameElement);

        return questionElement;
    }

    private Element setTextFieldWithCData(Element questionElement,
                                          String value) {
        Element HeadElement = doc.createElement("name");
        HeadElement.setAttribute("format", "html");
        Element HeadTextElement = doc.createElement("text");
        CDATASection HeadTextSection = doc.createCDATASection(value);
        HeadTextElement.appendChild(HeadTextSection);
        HeadElement.appendChild(HeadTextElement);
        questionElement.appendChild(HeadElement);

        return questionElement;
    }
}
