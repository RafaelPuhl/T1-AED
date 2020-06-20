public class App {
    public static void main(String[] args) {
        IndexCreator indexCreator = new IndexCreator("book.txt");
        Index index = indexCreator.getIndex();
        System.out.println(index);
    }
}
