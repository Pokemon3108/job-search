package by.daryazalevskaya.finalproject.service.requestbuilder;

import by.daryazalevskaya.finalproject.model.Contact;

import javax.servlet.http.HttpServletRequest;

public class ContactBuilder implements RequestBuilder<Contact> {
    @Override
    public Contact build(HttpServletRequest request) {
        String phone = request.getParameter("number");
        String email = request.getParameter("email");
        String skype = request.getParameter("skype");

        Contact contact = Contact.builder()
                .skype(skype)
                .email(email)
                .telephone(phone)
                .build();

        if (!request.getParameter("id").isEmpty()) {
            contact.setId(Integer.parseInt(request.getParameter("id")));
        }

        return contact;

    }
}
