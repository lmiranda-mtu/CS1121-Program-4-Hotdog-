/**
 * Prints the Bratty Buns menu: every topping combination with its price.
 */
public class HotDog {
   // base price in cents
   private static final int BASE_PRICE = 99;

   // topping names
   private static final String[] NAMES = {
      "Ketchup", "Mustard", "Chopped Onions", "Tomato Wedge",
      "Sweet Relish", "Sport Peppers", "Sauerkraut"
   };

   // topping prices in cents, same order as NAMES
   private static final int[] PRICES = { 15, 17, 20, 25, 30, 35, 45 };

   /**
    * Prints every dog on the menu, fewest toppings first.
    *
    * @return the number of combinations printed
    */
   public long printMenu( ) {
      // setup: 7 toppings, each a 0/1 choice, so 2^7 combinations
      int toppings = NAMES.length;
      int total = 1 << toppings;
      long count = 0;

      // loop over the number of toppings, none to all
      for ( int size = 0; size <= toppings; size++ ) {
         // loop over every bit pattern
         for ( int k = 0; k < total; k++ ) {
            // count down so earlier toppings come first
            int pattern = total - 1 - k;

            // skip patterns with the wrong number of toppings
            if ( Integer.bitCount( pattern ) != size ) {
               continue;
            }

            // start with the plain dog
            StringBuilder description = new StringBuilder( "Dog in Bun" );
            int price = BASE_PRICE;
            String separator = " with ";

            // add each topping whose bit is 1
            for ( int i = 0; i < toppings; i++ ) {
               // topping i lives in bit (toppings - 1 - i)
               if ( ( pattern & ( 1 << ( toppings - 1 - i ) ) ) != 0 ) {
                  // append the name and add its price
                  description.append( separator ).append( NAMES[i] );
                  price += PRICES[i];
                  separator = ", ";
               }
            }

            // print number, description, and price in dollars
            System.out.printf( "%3d %-85s $%d.%02d%n", count, description,
               price / 100, price % 100 );
            count++;
         }
      }

      // return the total printed
      return count;
   }

   /**
    * Runs the program.
    *
    * @param args unused
    */
   public static void main( String [ ] args ) {
      // build the stand and print the menu
      HotDog obj = new HotDog( );
      long count = obj.printMenu( );
      // verify all 2^7 = 128 combinations printed
      assert count == 128;
   }
}
