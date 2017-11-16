package Lesson02.Task1;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;


public class Test {

    public static void execute() {

        try {
            File file = new File("C:\\trains.xml");
            JAXBContext jaxbContext = JAXBContext.newInstance(Trains.class);

            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            Trains trains = (Trains) unmarshaller.unmarshal(file);
            System.out.println("All trains.");
            System.out.println(trains);

            filterTrains(trains);

            trains.add(new Train(3, "Kyiv", "Odessa", LocalDate.now(), LocalTime.of(15,30)));
            trains.add(new Train(4, "Lviv", "Kyiv", LocalDate.now(), LocalTime.of(18,45)));

            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(trains, file);
            System.out.println("New train list.");
            marshaller.marshal(trains, System.out);
        }
        catch(JAXBException e) {
            e.printStackTrace();
        }
    }

    private static void filterTrains(Trains trains) {

        System.out.println("Today from 15:00 to 19:00.");
        LocalTime from = LocalTime.of(15,0);
        LocalTime to = LocalTime.of(19,0);
        List<Train> filteredTrains = trains.getTrains().stream()
                .filter(t -> t.getDate().isEqual(LocalDate.now()) && t.getDeparture().isAfter(from) && t.getDeparture().isBefore(to))
                .collect(Collectors.toList());
        if(filteredTrains.size() > 0) {
            for(Train train : filteredTrains) {
                System.out.println(train);
            }
        }
        else {
            System.out.println("No match.");
        }
    }
}
