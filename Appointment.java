public class Appointment {

    private String appointmentType;
    private String appointmentDate;
    private String appointmentTime;
    private Client info;
    
    public Appointment(String appoiointmentTypeIn, String appointmentDateIn, String appointmentTimeIn) {
        appointmentType= appointmentTypeIn;
        appointmentTime= appointmentTimeIn;
        appointmentDate= appointmentDateIn;
    }
    public String getAppointmentType(){
        return appointmentType;
    }
    public static void cancellation(Client infor){
        Calender calender = Calender.getInstance;
        Date currentDate = calender.getTime();
        String currentDateTime = CurrentDate.toString();
        LocalDateTime appointmentDateTime = LocalDateTime.parse(appointmentDate+"T"+appointmentTime);
        long hourDifference= ChronoUnit.HOURS.between(currentDate, appointmentDateTime);   
        if(hourDifference >= 24){
            System.out.pritnln("Cancel");
        }
    }

}