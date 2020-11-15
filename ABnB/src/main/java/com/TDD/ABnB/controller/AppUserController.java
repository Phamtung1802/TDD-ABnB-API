package com.TDD.ABnB.controller;


import com.TDD.ABnB.exceptions.DuplilcateUserException;
import com.TDD.ABnB.models.AppUser;
import com.TDD.ABnB.services.app_user_service.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@CrossOrigin("*")
public class AppUserController{

    @Autowired
    private AppUserService appUserService;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;


    @GetMapping("/users")
    @Secured({"ROLE_USER"})
    public ResponseEntity<Iterable<AppUser>> showListUser() {
        Iterable<AppUser> appReviews = appUserService.findAll();
        for (AppUser user:appReviews
             ) {
            user.setPassword(null);
        }
        ResponseEntity<Iterable<AppUser>> res = new ResponseEntity<Iterable<AppUser>>(appReviews, HttpStatus.ACCEPTED);
        return res;
    }

    @GetMapping("/{id}")
    public ResponseEntity<AppUser> showUser(@PathVariable("id") Long id) {
        AppUser appUser = appUserService.findById(id);
        ResponseEntity<AppUser> res = new ResponseEntity<AppUser>(appUser, HttpStatus.ACCEPTED);
        return res;
    }

    @PostMapping()
    public ResponseEntity<AppUser> createUser(@RequestBody AppUser appUser) throws Exception {
        String userCheck= appUserService.checkUserAvailability(appUser.getName());
        String emailCheck= appUserService.checkEmailAvailability(appUser.getEmail());
        String phoneNumberCheck= appUserService.checkPhoneAvailability(appUser.getPhoneNumber());
        StringBuilder errorMessage=new StringBuilder("");
        if(userCheck!=null)
        {
            errorMessage.append(userCheck+"<br>");
        }
        if(emailCheck!=null) {
            errorMessage.append(emailCheck + "<br>");
        }
        if(phoneNumberCheck!=null) {
            errorMessage.append(phoneNumberCheck + "<br>");
        }
        if(errorMessage.length()>2){
            throw new DuplilcateUserException(errorMessage.toString());
        }

        appUser.setPassword(bCryptPasswordEncoder.encode(appUser.getPassword()));
        appUserService.save(appUser);
        ResponseEntity<AppUser> res = new ResponseEntity<AppUser>(appUser, HttpStatus.ACCEPTED);
        return res;
    }

    @PatchMapping("/{id}")
    @Secured({"ROLE_USER","ROLE_ADMIN","ROLE_RENTER"})
    public ResponseEntity<AppUser> updateUser(@PathVariable("id") Long id, @RequestBody AppUser appUser) throws Exception {
        AppUser userToUpdate= appUserService.findById(id);
        String editEmail = null;
        String editPhone =  null;
        appUser.setPassword(null);
        boolean isEmailChanged=((appUser.getEmail()!=null)&&(appUser.getEmail().equals(userToUpdate.getEmail())));
        boolean isPhoneChanged=((appUser.getPhoneNumber()!=null)&&(appUser.getPhoneNumber().equals(userToUpdate.getPhoneNumber())));
        if(appUser.getAddress()!=null){
            userToUpdate.setAddress(appUser.getAddress());
        }
        if(appUser.getRealName()!=null){
            userToUpdate.setRealName(appUser.getRealName());
        }

        if(appUser.getEmail()!=null&&!isEmailChanged){
            editEmail= appUserService.checkEmailAvailability(appUser.getEmail());
            userToUpdate.setEmail(appUser.getEmail());
        }
        if(appUser.getPhoneNumber()!=null&&!isPhoneChanged){
            editPhone= appUserService.checkPhoneAvailability(appUser.getPhoneNumber());
            userToUpdate.setPhoneNumber(appUser.getPhoneNumber());
        }

        StringBuilder messageError = new StringBuilder("");
        if (editEmail != null) {
            messageError.append(editEmail + "<br>");
        }
        if (editPhone != null) {
            messageError.append(editPhone + "<br>");
        }
        if (messageError.length() > 2) {
            throw new DuplilcateUserException(messageError.toString());
        }
        try {
            System.out.println(userToUpdate);
            appUserService.save(userToUpdate);
        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println(userToUpdate);
        userToUpdate.setPassword(null);
        ResponseEntity<AppUser> res = new ResponseEntity<AppUser>(userToUpdate, HttpStatus.ACCEPTED);
        return res;
    }


    @PatchMapping("edit-password/{id}")
    @Secured({"ROLE_USER","ROLE_ADMIN","ROLE_RENTER"})
    public ResponseEntity<AppUser> updatePassword(@PathVariable("id") Long id, @RequestBody AppUser appUser) throws Exception {
        AppUser userToUpdate= appUserService.findById(id);
        String editPassword = null;
        StringBuilder messageError=new StringBuilder("");
        String oldPassword= bCryptPasswordEncoder.encode(appUser.getName());
        if(!oldPassword.equals(userToUpdate.getPassword())){
            throw new DuplilcateUserException("Old Password does not match");
        }
        userToUpdate.setPassword(bCryptPasswordEncoder.encode(appUser.getPassword()));
        if (messageError.length() > 2) {
            throw new DuplilcateUserException(messageError.toString());
        }
        try {
            appUserService.save(userToUpdate);
        }catch (Exception e){
            e.printStackTrace();
        }
        ResponseEntity<AppUser> res = new ResponseEntity<AppUser>(userToUpdate, HttpStatus.ACCEPTED);
        return res;
    }


//    @PatchMapping("edit-password/{id}")
//    @Secured({"ROLE_USER","ROLE_ADMIN","ROLE_RENTER"})
//    public ResponseEntity<AppUser> changedPassword(@PathVariable("id") Long id,
//                                                   @RequestBody() ChangePassDTO changePassDTO) throws Exception {
//        AppUser userUpdatePass = appUserService.findById(id);
//
//        if (Objects.isNull(changePassDTO.getPresentPass())
//                || changePassDTO.getPresentPass().equals("")
//                || !bCryptPasswordEncoder.matches(changePassDTO.getPresentPass(), userUpdatePass.getPassword())) {
//            throw new DuplilcateUserException("Wrong Pass");
//        }
//        if (Objects.isNull(changePassDTO.getNewPass()) || changePassDTO.getNewPass().equals("")) {
//            throw new DuplilcateUserException("Cant set password null or empty string");
//        }
//
//        boolean isNotPasswordChanged = bCryptPasswordEncoder.matches(changePassDTO.getNewPass(), userUpdatePass.getPassword());
//
//        if (isNotPasswordChanged) {
//            throw new DuplilcateUserException("Same as old password ");
//        }
//        userUpdatePass.setPassword(bCryptPasswordEncoder.encode(changePassDTO.getNewPass()));
//        appUserService.save(userUpdatePass);
//        return new ResponseEntity<AppUser>(userUpdatePass, HttpStatus.ACCEPTED);
//
//    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable("id") Long id) {
        AppUser appUser = appUserService.findById(id);
        appUserService.delete(appUser);
    }
}
