package GetBetter;

import javafx.application.Platform;

public class MyCalendarController {

List<Button> dayButtonList = new ArrayList<>();
    int currentMonthNum;
    int currentYearNum;
    int currentDayNum;


    @FXML
    Label monthName;
    @FXML
    Label yearNumber;

    @FXML
    Button dayButton1;
    @FXML
    Button dayButton2;
    @FXML
    Button dayButton3;
    @FXML
    Button dayButton4;
    @FXML
    Button dayButton5;
    @FXML
    Button dayButton6;
    @FXML
    Button dayButton7;
    @FXML
    Button dayButton8;
    @FXML
    Button dayButton9;
    @FXML
    Button dayButton10;
    @FXML
    Button dayButton11;
    @FXML
    Button dayButton12;
    @FXML
    Button dayButton13;
    @FXML
    Button dayButton14;
    @FXML
    Button dayButton15;
    @FXML
    Button dayButton16;
    @FXML
    Button dayButton17;
    @FXML
    Button dayButton18;
    @FXML
    Button dayButton19;
    @FXML
    Button dayButton20;
    @FXML
    Button dayButton21;
    @FXML
    Button dayButton22;
    @FXML
    Button dayButton23;
    @FXML
    Button dayButton24;
    @FXML
    Button dayButton25;
    @FXML
    Button dayButton26;
    @FXML
    Button dayButton27;
    @FXML
    Button dayButton28;
    @FXML
    Button dayButton29;
    @FXML
    Button dayButton30;
    @FXML
    Button dayButton31;
    @FXML
    Button dayButton32;
    @FXML
    Button dayButton33;
    @FXML
    Button dayButton34;
    @FXML
    Button dayButton35;
    @FXML
    Button dayButton36;
    @FXML
    Button dayButton37;
    @FXML
    Button dayButton38;
    @FXML
    Button dayButton39;
    @FXML
    Button dayButton40;
    @FXML
    Button dayButton41;
    @FXML
    Button dayButton42;


    

    public void initialize() {

        dayButtonList.add(dayButton1);
        dayButtonList.add(dayButton2);
        dayButtonList.add(dayButton3);
        dayButtonList.add(dayButton4);
        dayButtonList.add(dayButton5);
        dayButtonList.add(dayButton6);
        dayButtonList.add(dayButton7);
        dayButtonList.add(dayButton8);
        dayButtonList.add(dayButton9);
        dayButtonList.add(dayButton10);
        dayButtonList.add(dayButton11);
        dayButtonList.add(dayButton12);
        dayButtonList.add(dayButton13);
        dayButtonList.add(dayButton14);
        dayButtonList.add(dayButton15);
        dayButtonList.add(dayButton16);
        dayButtonList.add(dayButton17);
        dayButtonList.add(dayButton18);
        dayButtonList.add(dayButton19);
        dayButtonList.add(dayButton20);
        dayButtonList.add(dayButton21);
        dayButtonList.add(dayButton22);
        dayButtonList.add(dayButton23);
        dayButtonList.add(dayButton24);
        dayButtonList.add(dayButton25);
        dayButtonList.add(dayButton26);
        dayButtonList.add(dayButton27);
        dayButtonList.add(dayButton28);
        dayButtonList.add(dayButton29);
        dayButtonList.add(dayButton30);
        dayButtonList.add(dayButton31);
        dayButtonList.add(dayButton32);
        dayButtonList.add(dayButton33);
        dayButtonList.add(dayButton34);
        dayButtonList.add(dayButton35);
        dayButtonList.add(dayButton36);
        dayButtonList.add(dayButton37);
        dayButtonList.add(dayButton38);
        dayButtonList.add(dayButton39);
        dayButtonList.add(dayButton40);
        dayButtonList.add(dayButton41);
        dayButtonList.add(dayButton42);


        currentMonthNum = MyCalendar.getSelectedDay().getDate().getMonthValue();
        currentYearNum = MyCalendar.getSelectedDay().getDate().getYear();
        currentDayNum = MyCalendar.getSelectedDay().getDate().getDayOfMonth();

        monthName.setText(LocalDate.of(currentYearNum, currentMonthNum, currentDayNum).getMonth().getDisplayName(TextStyle.FULL, Locale.ENGLISH));
        yearNumber.setText(String.valueOf(currentYearNum));

        int firstDayOfMonth = LocalDate.of(currentYearNum,currentMonthNum,currentDayNum-(currentDayNum-1)).getDayOfWeek().getValue();
        int dayNumeration = 1;
        int numberOfDaysInCurrentMonth = LocalDate.of(currentYearNum, currentMonthNum, currentDayNum).getMonth().maxLength();

        for(int i = 1; i<=dayButtonList.size(); i++) {
            if((i>=firstDayOfMonth) && (i<(numberOfDaysInCurrentMonth+firstDayOfMonth))) {
                dayButtonList.get(i-1).setText(String.valueOf(dayNumeration));
                dayNumeration++;
            }else {
                dayButtonList.get(i-1).setText("");

            }
        }
        System.out.println("tekst z przycisku 10: " +dayButton10.getText());






    }


    public void handleDayClick() {
        Platform.exit();

    }
}
