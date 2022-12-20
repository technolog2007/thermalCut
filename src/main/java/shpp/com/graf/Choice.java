package shpp.com.graf;

import javax.swing.*;
import java.awt.*;

public class Choice extends JFrame {
    JButton contour;
    JButton dimensions;
    boolean flag;

    public Choice(Container container) {

        container.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);//говорит как инициализировать компоненты(слева на право)
        container.setLayout(new GridLayout());//выбирает менеджер
        GridBagConstraints constraints = new GridBagConstraints();//вероятно создаёт менеджер(большую часть сверху я скопировал)
        // По умолчанию натуральная высота, максимальная ширина ↓
        constraints.fill = GridBagConstraints.NONE;
//        constraints.weightx = 0.5;
//        constraints.gridy = 0;// нулевая ячейка таблицы по вертикали

        contour = new JButton("Contour");//в скобках написан текст который содержится на кнопке
        constraints.gridx = 0;//расположение по х
        constraints.gridy = 1;//расположение по у
        container.add(contour, constraints);//добавление в контейнер

        dimensions = new JButton("Dimensions");
        constraints.gridx = 1;
        constraints.gridy = 1;
        container.add(dimensions, constraints);
    }

}
