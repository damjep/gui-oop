package OOP.ec22819.MP;//import java.util.List;

interface Visitable {

    Direction visit( // Returns direction the visitor leaves towards.
                     Visitor visitor,
                     Direction directionVistorArrivesFrom);
}