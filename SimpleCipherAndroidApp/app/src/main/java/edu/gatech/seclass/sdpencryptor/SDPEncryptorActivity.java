//Helping code: https://www.tutorialspoint.com/how-can-i-add-items-to-a-spinner-in-android
//Helping code: https://github.com/TheAlgorithms/Java/blob/master/ciphers/Caesar.java

package edu.gatech.seclass.sdpencryptor;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Typeface;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.view.View;
import android.widget.Spinner;
import android.widget.TextView;

import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;

public class SDPEncryptorActivity extends AppCompatActivity {

    String encryption_mode = "";

    public void HasInputValues() {
        EditText messageInput = (EditText) findViewById(R.id.messageInput);
        EditText shiftNumber = (EditText) findViewById(R.id.shiftNumber);

        String cipherText = "";

        TextView _cipherText = (TextView) findViewById(R.id.cipherText);
        //_cipherText.setText("");

        String inputMessage = messageInput.getText().toString();
        String inputShift = shiftNumber.getText().toString();

        boolean hasMessage = inputMessage.isEmpty(); //inputShift.length() < 1;
        if (hasMessage) {
            messageInput.setError("Missing Message");
        }  else if (inputShift.length() < 1 || inputShift.isEmpty()) {
            shiftNumber.setError("Invalid Shift Number");
        }

        boolean hasShiftNumber = inputShift.length() > 0;
        if ((inputMessage.length() > 0 || !inputMessage.isEmpty()) && !HasLetters(inputMessage)) {
            messageInput.setError("Invalid Message");
        }  else if ((!hasShiftNumber || !inputShift.isEmpty()) && !IsShiftNumberValid(inputShift)) {
            shiftNumber.setError("Invalid Shift Number");
        }else {

            int shift = Integer.parseInt(inputShift);

            //executes first NORMAL so the other modes get the encoded string
            cipherText = EncodeNORMAL(inputMessage, shift);

            //For NORMAL if user selects it back
            if (encryption_mode == "Normal") {

                cipherText = EncodeNORMAL(inputMessage, shift);
                _cipherText.setText(cipherText);

                //For REVERSE
            } else if (encryption_mode == "Reverse") {

                cipherText = EncodeREVERSE(cipherText);
                _cipherText.setText(cipherText);

                //For QWERTY
            } else if (encryption_mode == "QWERTY") {

                cipherText = EncodeQWERTY(cipherText);
                _cipherText.setText(cipherText);
            }
        }
    }

    //Checks if input message has at least one letter
    public boolean HasLetters(String input) {
        boolean hasLetters = input.matches(".*[a-zA-Z]+.*");

        if (hasLetters) {
            return true;
        }
        return false;
    }

    //Checks if shift number is valid
    public boolean IsShiftNumberValid(String shift) {

        try {
            int x = Integer.parseInt(shift);
            return true;
        } catch (NumberFormatException e) {

        }
        return false;
    }

    //Does NORMAL encoding
    public static String EncodeNORMAL(String message, int shiftNumber) {
        String encodedMessage = "";

        shiftNumber %= 26; //number of alphabet letters

        final int length = message.length();
        for (int i = 0; i < length; i++) {
            char currentAt = message.charAt(i);

            if (IsUppercaseLetter(currentAt)) {

                currentAt += shiftNumber;
                encodedMessage += (char) (currentAt > 'Z' ? currentAt - 26 : currentAt);

            } else if (IsLowercaseLetter(currentAt)) {

                currentAt += shiftNumber;
                encodedMessage += (char) (currentAt > 'z' ? currentAt - 26 : currentAt);

            } else {
                encodedMessage += currentAt;
            }
        }
        return encodedMessage;
    }

    //Checks if the char passed is uppercase
    private static boolean IsUppercaseLetter(char c) {
        return c >= 'A' && c <= 'Z';
    }

    //Checks if the char passed is lowercase
    private static boolean IsLowercaseLetter(char c) {
        return c >= 'a' && c <= 'z';
    }

    //Replaces to REVERSE alphabet
    public static String EncodeREVERSE(String text) {

        char[] str = text.toCharArray();

        int length = str.length;

        //Reverse alphabet
        for (int i = 0; i < length; i++) {
            switch (str[i]) {
                case 'A':
                    str[i] = 'Z';
                    break;
                case 'B':
                    str[i] = 'Y';
                    break;
                case 'C':
                    str[i] = 'X';
                    break;
                case 'D':
                    str[i] = 'W';
                    break;
                case 'E':
                    str[i] = 'V';
                    break;
                case 'F':
                    str[i] = 'U';
                    break;
                case 'G':
                    str[i] = 'T';
                    break;
                case 'H':
                    str[i] = 'S';
                    break;
                case 'I':
                    str[i] = 'R';
                    break;
                case 'J':
                    str[i] = 'Q';
                    break;
                case 'K':
                    str[i] = 'P';
                    break;
                case 'L':
                    str[i] = 'O';
                    break;
                case 'M':
                    str[i] = 'N';
                    break;
                case 'N':
                    str[i] = 'M';
                    break;
                case 'O':
                    str[i] = 'L';
                    break;
                case 'P':
                    str[i] = 'K';
                    break;
                case 'Q':
                    str[i] = 'J';
                    break;
                case 'R':
                    str[i] = 'I';
                    break;
                case 'S':
                    str[i] = 'H';
                    break;
                case 'T':
                    str[i] = 'G';
                    break;
                case 'U':
                    str[i] = 'F';
                    break;
                case 'V':
                    str[i] = 'E';
                    break;
                case 'W':
                    str[i] = 'D';
                    break;
                case 'X':
                    str[i] = 'C';
                    break;
                case 'Y':
                    str[i] = 'B';
                    break;
                case 'Z':
                    str[i] = 'A';
                    break;
                case 'a':
                    str[i] = 'z';
                    break;
                case 'b':
                    str[i] = 'y';
                    break;
                case 'c':
                    str[i] = 'x';
                    break;
                case 'd':
                    str[i] = 'w';
                    break;
                case 'e':
                    str[i] = 'v';
                    break;
                case 'f':
                    str[i] = 'u';
                    break;
                case 'g':
                    str[i] = 't';
                    break;
                case 'h':
                    str[i] = 's';
                    break;
                case 'i':
                    str[i] = 'r';
                    break;
                case 'j':
                    str[i] = 'q';
                    break;
                case 'k':
                    str[i] = 'p';
                    break;
                case 'l':
                    str[i] = 'o';
                    break;
                case 'm':
                    str[i] = 'n';
                    break;
                case 'n':
                    str[i] = 'm';
                    break;
                case 'o':
                    str[i] = 'l';
                    break;
                case 'p':
                    str[i] = 'k';
                    break;
                case 'q':
                    str[i] = 'j';
                    break;
                case 'r':
                    str[i] = 'i';
                    break;
                case 's':
                    str[i] = 'h';
                    break;
                case 't':
                    str[i] = 'g';
                    break;
                case 'u':
                    str[i] = 'f';
                    break;
                case 'v':
                    str[i] = 'e';
                    break;
                case 'w':
                    str[i] = 'd';
                    break;
                case 'x':
                    str[i] = 'c';
                    break;
                case 'y':
                    str[i] = 'b';
                    break;
                case 'z':
                    str[i] = 'a';
                    break;
                default:
                    break;
            }
        }
        return String.valueOf(str);
    }


    //Replaces to QWERTY alphabet
    public static String EncodeQWERTY(String text) {

        char[] str = text.toCharArray();

        int length = str.length;

        //QWERTY Alphabet
        for (int i = 0; i < length; i++) {
            switch (str[i]) {
                case 'A':
                    str[i] = 'Q';
                    break;
                case 'B':
                    str[i] = 'W';
                    break;
                case 'C':
                    str[i] = 'E';
                    break;
                case 'D':
                    str[i] = 'R';
                    break;
                case 'E':
                    str[i] = 'T';
                    break;
                case 'F':
                    str[i] = 'Y';
                    break;
                case 'G':
                    str[i] = 'U';
                    break;
                case 'H':
                    str[i] = 'I';
                    break;
                case 'I':
                    str[i] = 'O';
                    break;
                case 'J':
                    str[i] = 'P';
                    break;
                case 'K':
                    str[i] = 'A';
                    break;
                case 'L':
                    str[i] = 'S';
                    break;
                case 'M':
                    str[i] = 'D';
                    break;
                case 'N':
                    str[i] = 'F';
                    break;
                case 'O':
                    str[i] = 'G';
                    break;
                case 'P':
                    str[i] = 'H';
                    break;
                case 'Q':
                    str[i] = 'J';
                    break;
                case 'R':
                    str[i] = 'K';
                    break;
                case 'S':
                    str[i] = 'L';
                    break;
                case 'T':
                    str[i] = 'Z';
                    break;
                case 'U':
                    str[i] = 'X';
                    break;
                case 'V':
                    str[i] = 'C';
                    break;
                case 'W':
                    str[i] = 'V';
                    break;
                case 'X':
                    str[i] = 'B';
                    break;
                case 'Y':
                    str[i] = 'N';
                    break;
                case 'Z':
                    str[i] = 'M';
                    break;
                case 'a':
                    str[i] = 'q';
                    break;
                case 'b':
                    str[i] = 'w';
                    break;
                case 'c':
                    str[i] = 'e';
                    break;
                case 'd':
                    str[i] = 'r';
                    break;
                case 'e':
                    str[i] = 't';
                    break;
                case 'f':
                    str[i] = 'y';
                    break;
                case 'g':
                    str[i] = 'u';
                    break;
                case 'h':
                    str[i] = 'i';
                    break;
                case 'i':
                    str[i] = 'o';
                    break;
                case 'j':
                    str[i] = 'p';
                    break;
                case 'k':
                    str[i] = 'a';
                    break;
                case 'l':
                    str[i] = 's';
                    break;
                case 'm':
                    str[i] = 'd';
                    break;
                case 'n':
                    str[i] = 'f';
                    break;
                case 'o':
                    str[i] = 'g';
                    break;
                case 'p':
                    str[i] = 'h';
                    break;
                case 'q':
                    str[i] = 'j';
                    break;
                case 'r':
                    str[i] = 'k';
                    break;
                case 's':
                    str[i] = 'l';
                    break;
                case 't':
                    str[i] = 'z';
                    break;
                case 'u':
                    str[i] = 'x';
                    break;
                case 'v':
                    str[i] = 'c';
                    break;
                case 'w':
                    str[i] = 'v';
                    break;
                case 'x':
                    str[i] = 'b';
                    break;
                case 'y':
                    str[i] = 'n';
                    break;
                case 'z':
                    str[i] = 'm';
                    break;
                default:
                    break;
            }
        }
        return String.valueOf(str);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sdpencryptor);

        EditText inputMessage = (EditText) findViewById(R.id.messageInput);

        TextView _cipherText = (TextView) findViewById(R.id.cipherText);
        _cipherText.setTypeface(null, Typeface.BOLD);
        //_cipherText.setText("0"); //optional

        //sets cursor/focus on this textfield
        inputMessage.requestFocus();

        //create an object for Button
        Button btnRun = (Button) (findViewById(R.id.encryptButton));

        //create a click listener
        btnRun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HasInputValues();
            }
        });

        //add values to spinner (dropdown list)
        Spinner spinner = findViewById(R.id.targetAlphabet);
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("Normal");
        arrayList.add("Reverse");
        arrayList.add("QWERTY");
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, arrayList);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                encryption_mode = parent.getItemAtPosition(position).toString();

                //Toast.makeText(parent.getContext(), "Selected: " + encryption_mode, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }
}
