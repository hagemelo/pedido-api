package br.com.jhage.pedido_api.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 
 * @author Alexsander Melo
 * @since 05/02/2018
 *
 */
@CrossOrigin(origins = {"http://localhost:8080"}, maxAge = 3000)
@Controller
public class IndexController extends DefaultController{

	
	@RequestMapping("/")
    public String hello() {
       
        return "index";
}
}
