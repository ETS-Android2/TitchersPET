package pt.ubi.di.pmd.titcherspet;

import java.util.regex.Pattern;

public class InputChecker {

    public InputChecker(){}

    public boolean inputCheck(String input, String type){ // check if the given input is according to it's supposed type

        if(input.equals("")) // empty case
            return false;

        switch(type){

            case "phone_number": // case for phone numbers

                if(input.length()!=9) // too few or too many characters
                    return false;

                for(int i=0; i<input.length(); i++){

                    if((input.charAt(i))<48 || (input.charAt(i)>57))
                        return false;
                }

                break;

            case "email": // case for emails

                String[] split = input.split("@");

                if(split.length!=2)
                    return false;

                for(int i=0; i<split[0].length(); i++){ // before the @

                    if(split[0].charAt(i)=='.')
                        continue;

                    if(!(split[0].charAt(i)>=65 && split[0].charAt(i)<=90) // uppercase letter
                        && !(split[0].charAt(i)>=97 && split[0].charAt(i)<=122) // lowercase letter
                        && !(split[0].charAt(i)>=48 && split[0].charAt(i)<=57)) // a number
                        return false;
                }

                String[] split_after_at = split[1].split("\\.");

                if(split_after_at.length!=2)
                    return false;

                if(!split_after_at[0].equals("hotmail") && !split_after_at[0].equals("gmail")
                    && !split_after_at[0].equals("outlook") && !split_after_at[0].equals("ubi"))
                    return false;

                if(split_after_at[1].equals("com"))
                    return true;

                break;

            case "name": // case for names

                int num_words = ((input.split((" "))).length);
                int word_count = 1;

                for(int i=0; i<input.length(); i++){

                    if(input.charAt(i)==' ')
                        continue;

                    if(i!=0 && input.charAt(i-1)==' ')
                        word_count++;

                    if(!(input.charAt(i)>=65 && input.charAt(i)<=90) // uppercase letter
                            && !(input.charAt(i)>=97 && input.charAt(i)<=122)) // lowercase letter
                        return false;
                }

                if(word_count!=num_words) // too many spaces for the expected number of words (should be num(words) = num(spaces) + 1)
                    return false;

                break;

            case "password": // case for passwords

                if(input.length()<6) // password is too short
                    return false;

                int upper_letters = 0;
                int special_chars = 0;
                int numbers = 0;

                for(int i=0; i<input.length(); i++){

                    if((input.charAt(i)>=65 && input.charAt(i)<=90)) // uppercase letter
                        upper_letters++;

                    else if((input.charAt(i)>=33 && input.charAt(i)<=47) || (input.charAt(i)>=58 && input.charAt(i)<=64) ||
                            (input.charAt(i)>=91 && input.charAt(i)<=96) || (input.charAt(i)>=123 && input.charAt(i)<=126)) // special character
                        special_chars++;

                    else if((input.charAt(i)>=48 && input.charAt(i)<=57)) // number
                        numbers++;
                }

                if(upper_letters==0 || special_chars==0 || numbers==0) // not enough uppercase letters, special characters or numbers
                    return false;

                break;

            default:
                return false;
        }

        return true;
    }

}
