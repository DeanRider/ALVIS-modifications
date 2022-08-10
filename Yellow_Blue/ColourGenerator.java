// Alvis
//
// Alignment Diagrams in LaTeX and SVG
//
// Copyright 2018 Richard Leggett, Samuel Martin
// samuel.martin@earlham.ac.uk
// 
// This is free software, supplied without warranty.

package Alvis.Drawers;

import java.util.*;
import java.awt.Color;

/**
 *
 * @author martins
 */
public class ColourGenerator {
    ArrayList<Color> m_colours;
    int m_numberColours;
    
    public ColourGenerator(int n, float saturation, float brightness) 
    {
        assert(n>0);
        assert(0 <= saturation && saturation < 1.);
        assert(0 <= brightness && brightness < 1.);
        // generate the colours based on n evenly spaced hue's.
        m_colours = new ArrayList();
        m_numberColours = n;
        Random rng = new Random();
        int startHue = rng.nextInt(360);
        int delta = 360 / n;
        // find some number that is relatively prime to n
        // so that the order of colours is interesting
        int relativePrime = rng.nextInt(n) + 1;
        while(gcd(n, relativePrime) != 1)
        {
            relativePrime = rng.nextInt(n) + 1;
        }
        for(int i = 0; i < n; i++)
        {
            float hue = 0.16f + Math.abs(0.5f - ((float)((startHue + i * delta * relativePrime) % 360) /  360));
		// float hue now ranges between 0.16 and 0.66 yellow to green to navy
            m_colours.add(Color.getHSBColor(hue, saturation, brightness));
		// System.out.println(hue); // this line was used to detect values propduced
        }
    }
    
    public Color getColour(int i)
    {
        assert(0<= i && i < m_numberColours);
        return m_colours.get(i);
    }
    
    //srsly?
    private static int gcd(int p, int q) 
    {
        if (q == 0) {
            return p;
        }
        return gcd(q, p % q);
    }
    
}
