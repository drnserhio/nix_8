package ua.com.alevel.service;

import ua.com.alevel.MathSetImpl;
import ua.com.alevel.controller.MathSetConroller;
import ua.com.alevel.exception.EmptyArrayException;
import ua.com.alevel.exception.NotNullException;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Arrays;

import static ua.com.alevel.StringerUtil.*;
import static ua.com.alevel.constant.ExceptionConstant.*;

public class UserService {

    private MathSetImpl mathSet;


    public void clearWithArrayNumber(BufferedReader reader) {
        String n = "";
        try {

            if (mathSet == null ||
                    mathSet.getArrayMath().length < 1) {
                throw new EmptyArrayException(CREATE_SET);
            }
        } catch (EmptyArrayException e) {
            printWarn(EMPTY_ARRAY);
        } catch (NullPointerException e) {
            printWarn(EMPTY_STR + n);
        } catch (NumberFormatException e) {
            printWarn(e.getMessage());
        }
    }

    public void clear() {
        try {
            if (mathSet == null ||
                    mathSet.getArrayMath().length < 1) {
                throw new EmptyArrayException(CREATE_SET);
            }
            mathSet.clear();
        } catch (EmptyArrayException e) {
            printWarn(CREATE_SET);
        }
    }

    public void cutArray(BufferedReader reader) {
        String n = "";
        try {
            if (mathSet == null ||
                    mathSet.getArrayMath().length < 1) {
                throw new EmptyArrayException(CREATE_SET);
            }
        } catch (EmptyArrayException e) {
            printWarn(EMPTY_ARRAY);
        } catch (NullPointerException e) {
            printWarn(EMPTY_STR + n);
        } catch (NumberFormatException e) {
            printWarn(e.getMessage());
        }

    }

    public void toArrayWithIndex(BufferedReader reader) {
        String n = "";
        try {
            if (mathSet == null ||
                    mathSet.getArrayMath().length < 1) {
                throw new EmptyArrayException(CREATE_SET);
            }
        } catch (EmptyArrayException e) {
            printWarn(EMPTY_ARRAY);
        } catch (NullPointerException e) {
            printWarn(EMPTY_STR + n);
        } catch (NumberFormatException e) {
            printWarn(e.getMessage());
        }
    }

    public void toArray() {
        try {
            if (mathSet == null ||
                    mathSet.getArrayMath().length < 1) {
                throw new EmptyArrayException(CREATE_SET);
            }
            result(Arrays.toString(mathSet.toArray()));
        } catch (EmptyArrayException e) {
            printWarn(CREATE_SET);
        }
    }

    public void getMedian() {
        try {
            if (mathSet == null ||
                    mathSet.getArrayMath().length < 1) {
                throw new EmptyArrayException(CREATE_SET);
            }
            result(String.valueOf(mathSet.getMedian()));
        } catch (EmptyArrayException e) {
            printWarn(CREATE_SET);
        }
    }

    public void getAverage() {
        try {
            if (mathSet == null ||
                    mathSet.getArrayMath().length < 1) {
                throw new EmptyArrayException(CREATE_SET);
            }
            result(String.valueOf(mathSet.getAverage()));
        } catch (EmptyArrayException e) {
            printWarn(CREATE_SET);
        }
    }

    public void getMin() {
        try {
            if (mathSet == null ||
                    mathSet.getArrayMath().length < 1) {
                throw new EmptyArrayException(CREATE_SET);
            }
            result(String.valueOf(mathSet.getMin()));
        } catch (EmptyArrayException e) {
            printWarn(CREATE_SET);
        }
    }

    public void getMax() {
        try {
            if (mathSet == null ||
                    mathSet.getArrayMath().length < 1) {
                throw new EmptyArrayException(CREATE_SET);
            }
            result(String.valueOf(mathSet.getMax()));
        } catch (EmptyArrayException e) {
            printWarn(CREATE_SET);
        }
    }

    public void getValue(BufferedReader reader) {
        String n = "";
        try {
            if (mathSet == null ||
                    mathSet.getArrayMath().length < 1) {
                throw new EmptyArrayException(CREATE_SET);
            }
            n = reader.readLine();
            if (strIsEmpty(n)) {
                throw new NullPointerException(EMPTY_STR + n);
            }

            result(String.valueOf(mathSet.get(Integer.parseInt(n))));
        } catch (EmptyArrayException | IOException e) {
            printWarn(EMPTY_ARRAY);
        } catch (NullPointerException e) {
            printWarn(EMPTY_STR + n);
        } catch (NumberFormatException e) {
            printWarn(IS_NOT_NUMBER + n);
        }

    }

    public void sortAscWithNumber(BufferedReader reader) {
        String n = "";
        try {
            if (mathSet == null ||
                    mathSet.getArrayMath().length < 1) {
                throw new EmptyArrayException(CREATE_SET);
            }
        } catch (EmptyArrayException e) {
            printWarn(EMPTY_ARRAY);
        } catch (NullPointerException e) {
            printWarn(EMPTY_STR + n);
        } catch (NumberFormatException e) {
            printWarn(e.getMessage());
        }
    }

    public void sortAscwithIndex(BufferedReader reader) {
        String n = "";
        try {
            if (mathSet == null ||
                    mathSet.getArrayMath().length < 1) {
                throw new EmptyArrayException(CREATE_SET);
            }
        } catch (EmptyArrayException e) {
            printWarn(EMPTY_ARRAY);
        } catch (NullPointerException e) {
            printWarn(EMPTY_STR + n);
        } catch (NumberFormatException e) {
            printWarn(e.getMessage());
        }
    }

    public void sortAsc() {
        String n = "";
        try {
            if (mathSet == null ||
                    mathSet.getArrayMath().length < 1) {
                throw new EmptyArrayException(CREATE_SET);
            }
            mathSet.sortAsc();
        } catch (EmptyArrayException e) {
            printWarn(EMPTY_ARRAY);
        }
    }

    public void sortDescWithNumber(BufferedReader reader) {
        String n = "";
        try {
            if (mathSet == null ||
                    mathSet.getArrayMath().length < 1) {
                throw new EmptyArrayException(CREATE_SET);
            }
        } catch (EmptyArrayException e) {
            printWarn(EMPTY_ARRAY);
        } catch (NullPointerException e) {
            printWarn(EMPTY_STR + n);
        } catch (NumberFormatException e) {
            printWarn(e.getMessage());
        }
    }

    public void sortDescwithIndex(BufferedReader reader) {
        String n = "";
        try {
            if (mathSet == null ||
                    mathSet.getArrayMath().length < 1) {
                throw new EmptyArrayException(CREATE_SET);
            }
        } catch (EmptyArrayException e) {
            printWarn(EMPTY_ARRAY);
        } catch (NullPointerException e) {
            printWarn(EMPTY_STR + n);
        } catch (NumberFormatException e) {
            printWarn(e.getMessage());
        }
    }

    public void sortDesc() {
        String n = "";
        try {
            if (mathSet == null ||
                    mathSet.getArrayMath().length < 1) {
                throw new EmptyArrayException(CREATE_SET);
            }
            mathSet.sortDesc();
        } catch (EmptyArrayException e) {
            printWarn(EMPTY_ARRAY);
        }
    }

    public void interSectionWithVarargsMathSet(BufferedReader reader) {
        String n = "";
        try {
            if (mathSet == null ||
                    mathSet.getArrayMath().length < 1) {
                throw new EmptyArrayException(CREATE_SET);
            }
        } catch (EmptyArrayException e) {
            printWarn(EMPTY_ARRAY);
        } catch (NullPointerException e) {
            printWarn(EMPTY_STR + n);
        } catch (NumberFormatException e) {
            printWarn(e.getMessage());
        }
    }

    public void interSectionWithMathSet(BufferedReader reader) {
        String n = "";
        try {
            if (mathSet == null ||
                    mathSet.getArrayMath().length < 1) {
                throw new EmptyArrayException(CREATE_SET);
            }
        } catch (EmptyArrayException e) {
            printWarn(EMPTY_ARRAY);
        } catch (NullPointerException e) {
            printWarn(EMPTY_STR + n);
        } catch (NumberFormatException e) {
            printWarn(e.getMessage());
        }
    }

    public void joinWithVarArgsMathSet(BufferedReader reader) {
        String n = "";
        try {
            if (mathSet == null ||
                    mathSet.getArrayMath().length < 1) {
                throw new EmptyArrayException(CREATE_SET);
            }
        } catch (EmptyArrayException e) {
            printWarn(EMPTY_ARRAY);
        } catch (NullPointerException e) {
            printWarn(EMPTY_STR + n);
        } catch (NumberFormatException e) {
            printWarn(e.getMessage());
        }
    }

    public void joinWithMathSet(BufferedReader reader) {
        String n = "";
        try {
            if (mathSet == null ||
                    mathSet.getArrayMath().length < 1) {
                throw new EmptyArrayException(CREATE_SET);
            }
        } catch (EmptyArrayException e) {
            printWarn(EMPTY_ARRAY);
        } catch (NullPointerException e) {
            printWarn(EMPTY_STR + n);
        } catch (NumberFormatException e) {
            printWarn(e.getMessage());
        }
    }

    public void addVarargsNumber(BufferedReader reader) {
        String n = "";
        try {
            if (mathSet == null ||
                    mathSet.getArrayMath().length < 1) {
                throw new EmptyArrayException(CREATE_SET);
            }
        } catch (EmptyArrayException e) {
            printWarn(EMPTY_ARRAY);
        } catch (NullPointerException e) {
            printWarn(EMPTY_STR + n);
        } catch (NumberFormatException e) {
            printWarn(e.getMessage());
        }
    }

    public void addNumber(BufferedReader reader) {
        String n = "";
        try {
            if (mathSet == null ||
                    mathSet.getArrayMath().length < 1) {
                throw new EmptyArrayException(CREATE_SET);
            }
            n = reader.readLine();
            if (strIsEmpty(n)) {
                throw new NullPointerException("Entry empty date: " + n);
            }

            mathSet.add(Integer.parseInt(n));
        } catch (EmptyArrayException | IOException e) {
            printWarn(EMPTY_ARRAY);
        } catch (NullPointerException e) {
            printWarn(EMPTY_STR + n);
        } catch (NumberFormatException e) {
            printWarn(e.getMessage());
        }
    }

    public void createSetMenu(BufferedReader reader) throws Exception {
        try {
            if (mathSet != null) {
                throw new NotNullException("");
            }
            if (mathSet == null) {
                this.mathSet = new MathSetConroller().createSet(reader);
                result(mathSet.toString());
            }

        } catch (NullPointerException e) {
            printWarn(ERROR_CREATE_NEW_SET + mathSet.size());
            printWarn(CLEAR_SET);
        }
    }

    public void showSet() {
        try {
            if (mathSet == null ||
                    mathSet.getArrayMath().length < 1) {
                throw new EmptyArrayException(CREATE_SET);
            }
            mathSet.toPrint();
        } catch (EmptyArrayException e) {
            printWarn(CREATE_SET);
        }

    }

    public static void printWarn(String str) {
        System.out.println("_____________________________________________\n" +
                "        *********Warning********\n"
                + " |||||----" + str + "----|||||\n"
                + "_____________________________________________");
    }


}
