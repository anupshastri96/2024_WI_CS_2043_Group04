package dentalsoftware;

import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.io.Writer;
import java.io.BufferedReader;
import java.io.FileReader;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;


class FinancialStatementTest {
 
  @Test
  void testCreateStatement() throws IOException {
 ArrayList<Client> clients = new ArrayList<>();
 FinancialStatement state = new FinancialStatement(clients);
 
 ArrayList<Appointment> appointments1 = new ArrayList<>();
 LocalDateTime now = LocalDateTime.now();
 Appointment appointment1 = new Appointment(now, "Simple Cleaning", "Cleaning");
 appointment1.markAsPaid();
 
 appointments1.add(appointment1);
 Client client1 = new Client("Sierra", "Howe", 1001, "123 Cherry Lane","sierra.howe@unb.ca", 5074472772L, false, "Credit", appointments1);
 clients.add(client1);
 
 
 String fileName  = "financialStatement.txt";
 state.createStatement(fileName);
 
 StringBuilder fileContent = new StringBuilder();
 try(BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
String line;
while((line = reader.readLine()) != null) {
fileContent.append(line).append("\n");
}
 
 }
 
 String expectedOutput = "Financial Statement\n" + "Total Revenue: 200.0\n" + "Total Profit: 200.0\n" + "Appointment Prices: \n" + "Expenses per cleaning: $120.0\n" + "Expenses per filling: $0.0\n" + "Expenses per routine check-up: $0.0\n";
 
 assertEquals(expectedOutput, fileContent.toString());
 
 
 
 
  }
  
  @Test
  void testCalulateRevenue() {
  ArrayList<Client> clients = new ArrayList<>();
  ArrayList<Appointment> appointments1 = new ArrayList<>();
 LocalDateTime now = LocalDateTime.now();
 Appointment appointment1 = new Appointment(now, "Simple Cleaning", "Cleaning");
 appointments1.add(appointment1);
     Client client1 = new Client("Sierra", "Howe", 1001, "123 Cherry Lane","sierra.howe@unb.ca", 5074472772L, false, "Credit", appointments1);
     clients.add(client1);
 FinancialStatement state = new FinancialStatement(clients);
 appointment1.markAsPaid();
 
 
 
 
 double revenue = state.calculateRevenue();
 double expectedRevenue = appointment1.getPrice();
 
 assertEquals(expectedRevenue, revenue, 0.01);
 
  }
  
  @Test
  void testWritePrice() throws IOException{
  ArrayList<Client> clients = new ArrayList<>();
  ArrayList<Appointment> appointments1 = new ArrayList<>();
 LocalDateTime now = LocalDateTime.now();
 Appointment appointment1 = new Appointment(now, "Simple Cleaning", "Cleaning");
 appointments1.add(appointment1);
     Client client1 = new Client("Sierra", "Howe", 1001, "123 Cherry Lane","sierra.howe@unb.ca", 5074472772L, false, "Credit", appointments1);
     clients.add(client1);
 FinancialStatement state = new FinancialStatement(clients);
 appointment1.markAsPaid();
 
 
 String fileName = "financialStatement.txt";
 try(FileWriter writer  = new FileWriter(fileName)) {
    state.writePrice(writer);
 }
 
 StringBuilder fileInfo = new StringBuilder();
 try(BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
String line;
while((line = reader.readLine()) != null) {
fileInfo.append(line).append("\n");
}
 }
String expectedOutput = "Expenses per cleaning: $120.0\n" + "Expenses per filling: $0.0\n" + "Expenses per routine check-up: $0.0\n";
assertEquals(expectedOutput, fileInfo.toString());
 
 
  
  }
  
  @Test
  void testCalculateProfit() {
  ArrayList<Client> clients = new ArrayList<>();
  ArrayList<Appointment> appointments1 = new ArrayList<>();
 LocalDateTime now = LocalDateTime.now();
 Appointment appointment1 = new Appointment(now, "Simple Cleaning", "Cleaning");
 appointments1.add(appointment1);
     Client client1 = new Client("Sierra", "Howe", 1001, "123 Cherry Lane","sierra.howe@unb.ca", 5074472772L, false, "Credit", appointments1);
     clients.add(client1);
 FinancialStatement state = new FinancialStatement(clients);
 appointment1.markAsPaid();
 
 double revenue = state.calculateRevenue();
 
 double expectedExpenses = 120;
 double expectedProfit = revenue - expectedExpenses;
 
 double profit = state.calculateProfit(revenue);
 
 assertEquals(expectedProfit, profit);
  }
  
  

}
