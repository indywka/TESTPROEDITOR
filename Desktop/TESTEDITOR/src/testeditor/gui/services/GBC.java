package testeditor.gui.services;

import java.awt.*;

/**
 * .............. для сеточно-контейнерной компоновки
 */
public class GBC extends GridBagConstraints {
    public GBC(int gridx, int gridy, int gridwidth, int gridheight, int ipadx, int ipady, double weightx, double weighty) {
        this.gridx = gridx;//Номер ячейки в строке. Самая левая ячейка имеет номер 0.
        this.gridy = gridy;//Номер ячейки в столбце. Самая верхняя ячейка имеет номер 0
        this.gridwidth = gridwidth; //Количество ячеек в строке, занимаемых компонентом.
        this.gridheight = gridheight;//Количество ячеек в колонке, занимаемых компонентом. Целое типа int, по умолчанию 1.
        this.ipadx = ipadx;//внутренний отступ
        this.ipady = ipady;//Горизонтальные и вертикальные поля вокруг компонентов; по умолчанию 0, 0
        this.weightx = weightx;//Пропорциональное растяжение компонентов при изменении размера контейнера; по умолчанию 0, 0
        this.weighty = weighty;
    }

    public GBC setFill(int fill) { //Растяжение компонента для заполнения ячейки.
        this.fill = fill;
        return this;
    }

    public GBC setAnchor(int anchor) {   //Направление размещения компонента в контейнере
        this.anchor = anchor;
        return this;
    }

    public GBC setInsets(int top, int right, int bottom, int left) {  //Поля в контейнере. Объект класса Insets; по умолчанию объект с нулями
        this.insets = new Insets(top, left, bottom, right);
        return this;
    }
}
