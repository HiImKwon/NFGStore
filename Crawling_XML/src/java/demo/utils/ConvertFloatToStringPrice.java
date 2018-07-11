/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demo.utils;

/**
 *
 * @author Bui Quan
 */
public class ConvertFloatToStringPrice {

    public String convertToStringCheapestPrice(float cheapestFloat) {
        String cheapestPriceString = new String();
        String unCut = String.valueOf(cheapestFloat);
        unCut = unCut.replace(".0", "");
        boolean isCut = false;
        if (unCut.length() == 4 && !isCut) {
            cheapestPriceString = unCut.substring(0, 1) + "." + unCut.substring(1, unCut.length());
            isCut = true;
        }
        if (unCut.length() == 5 && !isCut) {
            cheapestPriceString = unCut.substring(0, 2) + "." + unCut.substring(2, unCut.length());
            isCut = true;
        }
        if (unCut.length() == 6 && !isCut) {
            cheapestPriceString = unCut.substring(0, 3) + "." + unCut.substring(3, unCut.length());
            isCut = true;
        }
        if (unCut.length() == 7 && !isCut) {
            cheapestPriceString = unCut.substring(0, 1) + "." + unCut.substring(1, unCut.length());
            cheapestPriceString = cheapestPriceString.substring(0, 5) + "." + cheapestPriceString.substring(5, cheapestPriceString.length());
            isCut = true;
        }

        if (isCut) {
            cheapestPriceString = cheapestPriceString.concat(" VNĐ");
        }

        return cheapestPriceString;
    }
}
