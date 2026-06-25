package com.guilherme.hotel.Controller;
import com.guilherme.hotel.Model.Room;
import com.guilherme.hotel.Service.RoomService;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

public class FrmRoomCrontroller {
    @FXML private TextField txtroom;
    @FXML private TextField txttype;
    @FXML private TextField txtpreco;
    @FXML private TextField txtstatus;

    private RoomService service = new RoomService();

    public void save(){
        Room room = new Room();
        room.setNumber(Integer.valueOf(txtroom.getText()));
        room.setDaily_rate(Double.valueOf(txtroom.getText()));
        room.setStatus(txtstatus.getText());
        room.setType(txttype.getText());
        service.save(room);
        Alert alert = new Alert(Alert.AlertType.INFORMATION, "The room was save");
    }
}
