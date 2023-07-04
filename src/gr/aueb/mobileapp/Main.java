package gr.aueb.mobileapp;

import gr.aueb.mobileapp.dao.IMobileContactDAO;
import gr.aueb.mobileapp.dao.MobileContactDAOImpl;
import gr.aueb.mobileapp.dto.MobileContactDTO;
import gr.aueb.mobileapp.dto.UserDetailsDTO;
import gr.aueb.mobileapp.model.MobileContact;
import gr.aueb.mobileapp.service.IMobileContactService;
import gr.aueb.mobileapp.service.MobileContactServiceImpl;
import gr.aueb.mobileapp.service.exceptions.MobileContactNotFoundException;
import gr.aueb.mobileapp.service.exceptions.PhoneNumberAlreadyExistsException;
import gr.aueb.mobileapp.service.exceptions.UserIdAlreadyExistsException;

import java.util.List;

public class Main {
    private final static IMobileContactDAO dao = new MobileContactDAOImpl();
    private final static IMobileContactService service = new MobileContactServiceImpl(dao);

    public static void main(String[] args) {
        try {
            UserDetailsDTO userDetailsDTO = new UserDetailsDTO(1L, "Athan.", "Andr.");
            MobileContactDTO mobileContactDTO = new MobileContactDTO(1L, userDetailsDTO, "123456789");

            UserDetailsDTO updatedUserDetailsDTO = new UserDetailsDTO(1L, "Athanassios", "Andr.");
            MobileContactDTO updatedMobileContactDTO = new MobileContactDTO(1L, updatedUserDetailsDTO, "123456789");
            service.insertMobileContact(mobileContactDTO);
            service.updateMobileContact(1L, updatedMobileContactDTO);

            List<MobileContact> contacts =  service.getAllMobileContacts();

            for (MobileContact contact : contacts) {
                System.out.println(contact);
            }

        } catch (PhoneNumberAlreadyExistsException | UserIdAlreadyExistsException | MobileContactNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
}
