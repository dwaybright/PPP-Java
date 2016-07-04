package Passcode.Card;

import Passcode.Passcode;


public interface CardFactory {

    String[] GenerateCard(Passcode passCodeGen, int cardIndex, int numPasscodePerCard);
}
