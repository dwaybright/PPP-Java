package Passcode.Card;

import Passcode.Passcode;


public class BasicCardFactoryImpl implements CardFactory {

    public String[] GenerateCard(Passcode passCodeGen, int cardIndex, int numPasscodePerCard) {
        String[] output = new String[numPasscodePerCard];

        for(int i = 0; i < numPasscodePerCard; i++) {
            output[i] = passCodeGen.GetPassCode(4, (cardIndex * numPasscodePerCard) + i);
        }

        return output;
    }
}
