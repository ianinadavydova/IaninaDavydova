package hw7hw8.entities;

import com.epam.jdi.tools.DataClass;
import hw3.enums.Element;
import hw4.enums.Vegetable;

import java.util.List;

// TODO naming
public class MetalsColorsEntity extends DataClass<MetalsColorsEntity> {
    public String summaryOdd, summaryEven;
    public List<Element> elements;
    public String colors;
    public String metals;
    public List<Vegetable> vegetables;
}
