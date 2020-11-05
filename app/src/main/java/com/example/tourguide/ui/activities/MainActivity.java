package com.example.tourguide.ui.activities;

import android.annotation.SuppressLint;
import android.content.res.Configuration;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tourguide.R;
import com.example.tourguide.data.model.Cities;
import com.example.tourguide.data.model.CityEvents;
import com.example.tourguide.data.model.CityPlaces;
import com.example.tourguide.ui.adapters.city.CityAdapter;
import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.Locale;

import io.github.sporklibrary.Spork;
import io.github.sporklibrary.android.annotations.BindLayout;
import io.github.sporklibrary.android.annotations.BindView;

/**
 * This is the mainActivity class where main page of the app is generated
 */

@SuppressLint("NonConstantResourceId")
@BindLayout(R.layout.activity_main)
public class MainActivity extends AppCompatActivity {

    // RecyclerView holding the list of the cities
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    // Required adapter for recyclerview
    CityAdapter adapter;

    // Some input data cityNames, countryNames, cityImages, cityLocationImages, cityLocations
    String[] citiesName = {"Cairo"};
    String[] countriesName = {"Egypt"};
    int[] Cimages = {R.drawable.cairo};
    int[] CimagesL = {R.drawable.cairo_location};
    LatLng[] locations = {new LatLng(30.044420, 31.235712)};

    // List of the input data to be displayed on the app
    ArrayList<Cities> cities = new ArrayList<>();

    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Spork.bind(this);

        // Set the app language to english
        Configuration config;
        config = new Configuration(getResources().getConfiguration());
        config.locale = Locale.ENGLISH;
        config.setLayoutDirection(new Locale("en"));
        getResources().updateConfiguration(config, getResources().getDisplayMetrics());

        // ArrayList of Cities
        getCities();

        // Generating the adapter and set it to recyclerview
        adapter = new CityAdapter(this,this, cities);
        LinearLayoutManager llm = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(llm);
        recyclerView.setAdapter(adapter);
    }

    /**
     * Method that sets the required data to be displayed and add it to the list.
     */
    private void getCities() {
        for (int i = 0; i < citiesName.length; i++){
            cities.add(new Cities(citiesName[i], countriesName[i],
                    Cimages[i],CimagesL[i],  locations[i]));
        }

        ArrayList<CityPlaces> p = new ArrayList<>();
        p.add(new CityPlaces("place", new int[]{R.drawable.coptic_cairo, R.drawable.coptic_cairo1, R.drawable.coptic_cairo2, R.drawable.coptic_cairo3, R.drawable.coptic_cairo4, R.drawable.coptic_cairo5}, "This small church-filled cluster of twisty laneways lies within the walls of Old Babylon where the Roman Emperor Trajan first built a fortress along the Nile. Parts of the Roman towers still preside over the main street.\n" +
                "\n" +
                "The Coptic Museum here contains a wealth of information on Egypt's early Christian period and is home to one of Egypt's finest collections of Coptic art. Next door, the 9th-century Hanging Church contains some beautiful examples of Coptic architecture. Founded in the 4th century, the church was originally built over the Roman gate towers (hence the name) and was substantially rebuilt during the 9th century.\n" +
                "\n" +
                "For many Christian travelers though, the real highlight of a visit to this district is the Church of St. Sergius and Bacchus, where local legend says the Virgin Mary, baby Jesus, and family sheltered during King Herod's massacre of male babies. Farther into the quarter, you come to the Ben Ezra Synagogue, which is said to be built near the spot where the baby Moses was found in the reeds. Just outside the quarter, you can also visit the Mosque of Amr Ibn al-As; the first mosque built in Egypt.\n" +
                "\n" +
                "Coptic Cairo is easiest reached by taking the Cairo Metro to Mar Girgis station.", "Old Cairo (Coptic Cairo)",
                new LatLng(30.006, 31.231), "Sharia Mar Girgis, south of Downtown"
        ));
        p.add(new CityPlaces("place", new int[]{R.drawable.al_azhar_mosque, R.drawable.al_azhar_mosque1, R.drawable.al_azhar_mosque2, R.drawable.al_azhar_mosque3, R.drawable.al_azhar_mosque4, R.drawable.al_azhar_mosque5}, "Al-Azhar Mosque is the finest building of Cairo's Fatimid era and one of the city's earliest surviving mosques, completed in AD 972. It's also one of the world's oldest universities — Caliph El-Aziz bestowed it with the status of university in AD 988 (the other university vying for \"oldest\" status is in Fes) and today, Al-Azhar University is still the leading theological center of the Islamic world.\n" +
                "\n" +
                "The main entrance is the Gate of the Barbers on the northwest side of the building, adjoining the neo-Arab facade built by Abbas II. Leave your shoes at the entrance and walk into the central courtyard. To your right is the El-Taibarsiya Medrese, which has a mihrab (prayer niche) dating from 1309. From the central courtyard, you get the best views of the mosque's five minarets, which cap the building. Across the courtyard is the main prayer hall, spanning a vast 3,000 square meters. The front half is part of the original building, while the rear half was added by Abd el-Rahman.\n" +
                "\n" +
                "Al-Azhar Mosque is right in the heart of the Islamic Cairo district and easy to reach by taxi. Al-Azhar Street runs east from Midan Ataba in the downtown area right to the square where the mosque sits.", " Al-Azhar Mosque",new LatLng(30.045665,31.262695), "Al-Azhar Street, Islamic Cairo District"));
        p.add(new CityPlaces("place", new int[]{R.drawable.the_egyptian_museum, R.drawable.the_egyptian_museum1, R.drawable.the_egyptian_museum2, R.drawable.the_egyptian_museum3, R.drawable.the_egyptian_museum4, R.drawable.the_egyptian_museum5}, "The absolutely staggering collection of antiquities displayed in Cairo's Egyptian Museum makes it one of the world's great museums. You would need a lifetime to see everything on show.\n" +
                "\n" +
                "The museum was founded in 1857 by French Egyptologist August Mariette and moved to its current home — in the distinctive powder-pink mansion in Downtown Cairo — in 1897. Yes, the collection is poorly labeled and not well set out due to limits of space (and only a fraction of its total holdings are actually on display). It also suffers currently with some empty cases due to artifacts having been transferred to the GEM, but you still can't help being impressed by the sheer majesty of the exhibits.\n" +
                "\n" +
                "If you're pressed for time, make a beeline straight for the Tutankhamun Galleries. The treasures displayed here were all found in the tomb of Tutankhamun, son-in-law and successor of Amenophis IV (later Akhenaten), who died at the age of 18. The tomb, discovered by Howard Carter in the Valley of the Kings in 1922, contained the largest and richest assemblage of grave goods ever found intact in an Egyptian tomb.\n" +
                "\n" +
                "Highlights include Tutankhamun's death mask and sarcophagi (Room 3), the pharaoh's lion throne (Room 35), and his fascinating wardrobe collection (Room 9). Afterwards, don't miss a wander through the Egyptian jewelry collection (Room 4), which contains more bling than you'll ever see again in one lifetime, and finish off by viewing the Royal Mummies Collection (Room 56 & 46), where you can say hello to Hatshepsut, Tuthmosis II, Ramses II, and Seti I in person.\n" +
                "\n" +
                "Even when the GEM opens, this iconic building will still be used as a museum. It has not been announced which collections will stay here and which will be moved — except for the Tutankhamun Galleries, which will all eventually be transferred to the GEM.\n" +
                "\n" +
                "The Egyptian Museum sits right beside Midan Tahrir, the central square of Downtown Cairo. The easiest way to arrive here is to take the Cairo Metro to Sadat station (on Midan Tahrir) and follow the exit signs to the museum.","The Egyptian Museum",new LatLng(30.047773,31.233700), "Midan al-Tahrir, Downtown"));

        p.add(new CityPlaces("restaurant", new int[]{R.drawable.le_patcha_restaurant},"Dress code: smart casual\n" +
                "Price range: 300-600 EGP per person\n" +
                "Alcohol served: yes\n" +
                "Nile view: yes\n" +
                "Vegan & vegetarian options available: yes\n" +
                "We’re starting off the list with the international award-winning Le Pacha because it’s technically not one, not two, but several great restaurants, all in one Nile-docked boat.\n" +
                "Le Pacha offers 7 different restaurants and one bar/lounge/nightspot-ish venue. Our absolute favorites are: Carlo’s, where the menu is a mix of all different cuisines (including local Egyptian favorites) and you can order shisha; L’Asiatique, their pan-Asian restaurant which was voted 3rd best in Africa; and Piccolo Mondo, which is Italian overlooking the Nile. \n" +
                "Let us be the first to tell you -- their food is good, always. And while that might seem like a given, in Egypt it really is not. Most restaurants fail to deliver consistency (it’s pretty sad when you can tell when there’s a different chef), but Le Pacha’s restaurants deliver Every. Single. Time. \n" +
                "The other restaurants they have: Le Steak (French), Maharani (Indian), River Boat (Lebanese), Le Tarbouche (Egyptian) and Le J.Z. (lounge). " +
                "Carlo’s menu => https://www.elmenus.com/cairo/carlos-7k5g .\n" +
                "L’Asiatique menu => https://www.elmenus.com/cairo/lasiatique-3v83 .","Le Pacha 1901",new LatLng(30.051818,31.227779),"Zamalek"));
        p.add(new CityPlaces("restaurant",new int[]{R.drawable.abou_el_sid},"Dress code: casual\n" +
                "Price range: 200-500 EGP per person\n" +
                "Alcohol served: yes\n" +
                "Nile view: no\n" +
                "Vegan & vegetarian options available: yes\n" +
                "Abou El Sid is the must-go-to restaurant if you want classic Egyptian dishes (check out our article 12 Local Egyptian Foods You Need To Try => https://www.localguidetoegypt.com/post/12-local-egyptian-foods-you-need-to-try) in a super ambient atmosphere (the decor, music, and wall art is enough to make it worth it). They also offer shisha and Egyptian twists on alcoholic cocktails, like vodka with fresh sugarcane juice. \n" +
                "Dishes of theirs that are definitely worth trying: the sharqisseya, a chicken dish with walnut sauce; stuffed vine leaves; the Egyptian moussaka, which is an eggplant stew served with rice; and a mix and match of all their various Egyptian mezzes. Drool. \n" +
                "Abou El Sid menu => https://www.elmenus.com/cairo/abou-el-sid-595q .","Abou El Sid",new LatLng(30.059349,31.224254),"Zamalek, Heliopolis, 6th of October and Tagamo’a."));
        p.add(new CityPlaces("restaurant",new int[]{R.drawable.pier_88},"Dress code: Smart casual\n" +
                "Price range: 300-600 EGP per person\n" +
                "Alcohol served: yes\n" +
                "Nile view: yes\n" +
                "Vegan & vegetarian options available: yes\n" +
                "Pier 88 started as a restaurant/bar in the seaside town of El Gouna on the Red Sea, and its food and overall vibe became so popular that the owners decided to take the plunge and open up shop in Cairo. \n" +
                "Pier 88 sits on the upper level of a docked boat in Zamalek overlooking the Nile, and during the day it’s one of those quietly sophisticated restaurants, complete with an open kitchen and drinks overlooking the water. \n" +
                "At night it gets more of a nightlife feel to it, and reservations are recommended. On weekend nights the music gets turned up and there’s occasionally dancing by the tables (we don’t recommend eating when the dancing gets started, because you won’t find much room to wield your knife and fork). \n" +
                "Pier 88 menu => https://www.elmenus.com/cairo/pier-88-nn7v. ","Pier 88",new LatLng(30.052904,31.226749),"Zamalek"));

        ArrayList<CityEvents> e = new ArrayList<>();
        e.add(new CityEvents(R.drawable.halloween_event,"Star Wars Halloween Party with Art Cafe\n" +
                "It s time for Our famous Halloween Party\n" +
                "this year with a new look & a new theme and for sure in both locations ....\n" +
                "Maadi & Sheikh Zayed\n" +
                "\n" +
                "\"SATR WARS HALLOWEEN PARTY\"\n" +
                "Saturday October 31st Maadi Branch\n" +
                "Friday october 30th Zayed branch\n" +
                "\n" +
                "4-7 pm\n" +
                "4 -9 years old\n" +
                "(Less than 6 years could need some help from their parents )\n" +
                "\n" +
                "Ticket 350 LE / child\n" +
                "\n" +
                "Tickets will be available as of Sunday October 18th in both branches\n" +
                "\n" +
                "Party Program\n" +
                "from 4 to 7:00 Art Activities\n" +
                "Costumes ,trick or treat bag making, activity table, face painting and much more interesting monsters activities ....\n" +
                "\n" +
                "photo booth during the party for some memorable halloween photos !!!\n" +
                "\n" +
                "Trick Or Treat\n" +
                "(Note: Kids SHOULD be accompanied by any of their parents during the trick or treat)\n" +
                "\n" +
                "* We will be having a costume making station so kids who dont have their costumes don't worry you will make your own .\n" +
                "*Ticket are only for kids , Parents are welcomed to join helping their kids in their art projects .\n" +
                "\n" +
                "* tickets are very limited so make sure to buy your ticket before the party and show it at the entrance *\n" +
                "*Enjoy ....\n" +
                "-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-\n" +
                "\n" +
                "Art Cafe contacts\n" +
                "Maadi branch\n" +
                "Facebook page\n" +
                "https://m.facebook.com/ArtCafeEgypt\n" +
                "Contacts :\n" +
                "01227050753\n" +
                "Info@artcafe-Egypt.com\n" +
                "Www.artcafe-Egypt.com\n" +
                "\n" +
                "Address :\n" +
                "10b road 11 between 85 & 86\n" +
                "Main door from canal street ( behind McDonald's street 9)\n" +
                "https://goo.gl/maps/ai6xskfdvvo\n" +
                "\n" +
                "Working hours :\n" +
                "from Saturday to Wednesday from 10am -10pm & Thursday from 10am-7pm\n" +
                "Closed on Friday.\n" +
                "\n" +
                "-------—•—•—•—•—•—•—•—•—•------\n" +
                "\n" +
                "Sheikh Zayed branch\n" +
                "Culture LAB /galleria40\n" +
                "\n" +
                "Contacts :\n" +
                "01211170729\n" +
                "Galleria40@artcafe-Egypt.com\n" +
                "Www.artcafe-Egypt.com\n" +
                "\n" +
                "Address:\n" +
                "Gallera40 mehwar 26 July\n" +
                "Culture LAB\n" +
                "Shop#108\n" +
                "https://goo.gl/maps/fErxDiMDwhP2\n" +
                "\n" +
                "Working hours :\n" +
                "Everyday from 12pm - 10 pm\n" +
                "\n" +
                "\n" +
                "You may also like the following events from Art Café-Egypt:\n" +
                "\n" +
                "Happening on, 6th July, 05:00 pm, Arabic Calligraphy - Zayed Branch in Cairo\n" +
                "Happening on, 7th July, 12:00 pm, Vibrant Painting - Virtual class in Cairo\n" +
                "Happening on, 8th July, 11:00 am, Basic Photography - Maadi Branch in Cairo\n" +
                "Also check out other Kids Events & Activities in Cairo, Parties in Cairo, Entertainment Events in Cairo.","Star Wars Halloween Party",new LatLng(29.954473,31.264359),"Art Café-Egypt, 10b road 11, Maadi, Cairo, Egypt"));

        cities.get(0).setPlaces(p);
        cities.get(0).setEvents(e);
    }

}