package ru.gb.gbshopmay.web.conntroller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import ru.gb.gbshopmay.service.CaptchaGenerator;

@Controller
public class HomeController {

    private CaptchaGenerator captchaGenerator;

    @GetMapping
    public String getHomePage() {
        return "redirect:/product/all";
    }

//    @GetMapping(value = "/captcha", produces = MediaType.IMAGE_PNG_VALUE)
//    public byte[] getCaptcha(){
//        Map<String, BufferedImage> captcha = captchaGenerator.generateCaptcha();// генерирует картинку с капчой со словом кторое содержится в строке
//        // captcha. key класть в сессиию
//        // captcha.value класть в ImageIO
//        // положить в сессию captchaString
//
//        ImageIO.write(captha.value, "png", new ByteArrayOutputStream())
//    }
    // тоже самое что в картинках к продуктам, только надо имя класть в сессию
}
