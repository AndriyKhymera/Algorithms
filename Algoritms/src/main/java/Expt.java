public class Expt {

    interface Square{
        double square();
    }

    interface Perimeter{
        double perimeter();
    }

    static class Figure implements Square, Perimeter{
        @Override
        public double square() {
            System.out.println("square");
            return 0;
        }

        @Override
        public double perimeter() {
            System.out.println("perimeter");
            return 0;
        }
    }


    public static void main(String[] args) {
        Perimeter perimeter = new Figure();
        Square square = new Figure();
        Figure figure = new Figure();
        System.out.println(perimeter.perimeter());
        System.out.println(square.square());
        System.out.println(figure.perimeter());
        System.out.println(figure.square());

     }
}
