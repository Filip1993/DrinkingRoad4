package com.filipkesteli.drinkingroad4;

import com.filipkesteli.drinkingroad4.countries.Belarus;
import com.filipkesteli.drinkingroad4.countries.Bhutan;
import com.filipkesteli.drinkingroad4.countries.Croatia;
import com.filipkesteli.drinkingroad4.countries.Ireland;
import com.filipkesteli.drinkingroad4.countries.Israel;
import com.filipkesteli.drinkingroad4.countries.Macedonia;
import com.filipkesteli.drinkingroad4.countries.Pakistan;

/**
 * Created by Filip on 31.5.2016..
 */
public class PROBAMapsActivity {

    private Pakistan pakistan;
    private Bhutan bhutan;
    private Israel israel;
    private Macedonia macedonia;
    private Ireland ireland;
    private Croatia croatia;
    private Belarus belarus;

    private void initCountries() {
        pakistan = new Pakistan(1, 4);
        bhutan = new Bhutan(2, 5);
        israel = new Israel(3, 6);
        macedonia = new Macedonia(4, 7);
        ireland = new Ireland(5, 8);
        croatia = new Croatia(6, 9);
        belarus = new Belarus(7, 10);
    }
}
