import static com.company.Quiz.MAX_VALUE;
import static com.company.Quiz.MIN_VALUE;
 
class Main {
 
    /**
     * metoda main powinna implementowac algorytm do
     * jak najszybszego wyszukiwania wartosci
     * zmiennej digit z klasy QuizImpl (zakladamy ze
     * programista nie zna zawartosci klasy QuizImpl).
     * Nalezy zalozyc, ze pole digit w klasie QuizImpl
     * moze w kazdej chwili ulec zmianie. Do wyszukiwania
     * odpowiedniej wartosci nalezy wykorzystywac tylko
     * i wylacznie metode isCorrectValue - jesli metoda
     * przestanie rzucac wyjatki wowczas mamy pewnosc ze
     * poszukiwana zmienna zostala odnaleziona.
     */
    public static void main(String[] args) {
 
        Quiz quiz = new QuizImpl();
 
        int max = MAX_VALUE;
        int min = MIN_VALUE;
 
        int digit = ( max + min) / 2; // zainicjuj zmienna
 
        for(int counter = 1; ;counter++) {
 
            try {
                quiz.isCorrectValue(digit);
                System.out.println("Trafiona proba!!! Szukana liczba to: "
                        + digit + " Ilosc prob: " + counter);
                break;
 
            } catch(Quiz.ParamTooLarge e) {
                System.out.println("Argument za duzy!!!");
                // implementacja logiki...
 
                max = digit;
                digit = ( max + min) / 2;
 
            } catch(Quiz.ParamTooSmall e) {
                System.out.println("Argument za maly!!!");
                // implementacja logiki...
 
                min = digit;
                digit = ( max + min) / 2;
 
            }
        }
    }
}
 
 
/** interfejs dla gry Quiz */
interface Quiz {
 
    // minimalny zakres poszukiwan
    int MIN_VALUE = 0;
    // maksymalny zakres poszukiwan
    int MAX_VALUE = 1000;
 
    /**
     * metoda rzuca wyjatek ParamTooLarge w
     * przypadku gdy parametr wejsciowy jest wiekszy
     * od oczekiwanej zmiennej, w przeciwnym wypadku
     * rzuca wyjatek ParamTooSmall.
     */
    void isCorrectValue(int value)
            throws Quiz.ParamTooLarge, Quiz.ParamTooSmall;
 
    class ParamTooLarge extends Exception {}
    class ParamTooSmall extends Exception {}
}
 
class QuizImpl implements Quiz{
 
    private int digit;
 
    public QuizImpl() {
        this.digit = 254;    // zmienna moze ulegac zmianie!
    }
 
    @Override
    public void isCorrectValue(int value) throws ParamTooLarge, ParamTooSmall {
        if(value > digit){
            throw new ParamTooLarge();
        }
        if(value < digit){
            throw new ParamTooSmall();
        }
    }
}