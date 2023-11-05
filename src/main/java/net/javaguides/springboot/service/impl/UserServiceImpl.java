package net.javaguides.springboot.service.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.javaguides.springboot.controller.UserController;
import net.javaguides.springboot.dto.UserDto;
import net.javaguides.springboot.entity.User;
import net.javaguides.springboot.exception.EmailAlreadyExistsException;
import net.javaguides.springboot.exception.ResourceNotFoundException;

import net.javaguides.springboot.repository.UserRepository;
import net.javaguides.springboot.service.UserService;
import org.apache.logging.log4j.util.Strings;
import org.modelmapper.ModelMapper;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    private ModelMapper modelMapper;
    
   
    public String retrieveDataFromDatabaseOrOtherSource(String email) {
    	 log.info("key:"+email);
        // Mettez ici la logique de récupération des données à partir de votre source de données réelle.
        // Cela pourrait être une requête à une base de données, une API externe, un fichier, etc.
        // Vous devez implémenter cette méthode en fonction de votre propre logique de récupération de données.

        // Par exemple, ici, nous supposons simplement que nous retournons une chaîne vide.
    	
    	Optional<User> optionalUser = userRepository.findByEmail(email);
    	String val = String.valueOf(optionalUser.isPresent());
    	 log.info("val:"+val);
        return val;
    }
    @Cacheable("myCache")
    public String getUserByEmail(String email) {
    	log.info("befor cache:"+email);
    	
        String val= retrieveDataFromDatabaseOrOtherSource(email);
        log.info("after: cache "+val);
        return val;
    }
    @Override

    public UserDto createUser(UserDto userDto) {

        // Convert UserDto into User JPA Entity
       // User user = UserMapper.mapToUser(userDto);

        //User user = modelMapper.map(userDto, User.class);
    	log.info("befor user:"+userDto.toString());
    	boolean xbool =Boolean.valueOf(getUserByEmail(userDto.getEmail()));
    	log.info("after:"+userDto.toString());
        
        if(xbool ){
        	
            throw new EmailAlreadyExistsException("Email Already Exists for User");
        }
        
        User user = modelMapper.map(userDto, User.class);
       

        User savedUser = userRepository.save(user);
        log.info("user2 saved:"+savedUser.toString());

        // Convert User JPA entity to UserDto
        //UserDto savedUserDto = UserMapper.mapToUserDto(savedUser);

        UserDto savedUserDto = modelMapper.map(savedUser, UserDto.class);

        //UserDto savedUserDto = AutoUserMapper.MAPPER.mapToUserDto(savedUser);

        return savedUserDto;
    }
    

    @Override
    public UserDto getUserById(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new ResourceNotFoundException("User", "id", userId)
        );
        //return UserMapper.mapToUserDto(user);
        return modelMapper.map(user, UserDto.class);
        //return AutoUserMapper.MAPPER.mapToUserDto(user);
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = userRepository.findAll();
//        return users.stream().map(UserMapper::mapToUserDto)
//                .collect(Collectors.toList());

       return users.stream().map((user) -> modelMapper.map(user, UserDto.class))
               .collect(Collectors.toList());

        //return users.stream().map((user) -> AutoUserMapper.MAPPER.mapToUserDto(user))
        //        .collect(Collectors.toList());
    }

    @Override
    public UserDto updateUser(UserDto user) {

        User existingUser = userRepository.findById(user.getId()).orElseThrow(
                () -> new ResourceNotFoundException("User", "id", user.getId())
        );

        existingUser.setFirstName(user.getFirstName());
        existingUser.setLastName(user.getLastName());
        existingUser.setEmail(user.getEmail());
        User updatedUser = userRepository.save(existingUser);
        //return UserMapper.mapToUserDto(updatedUser);
        return modelMapper.map(updatedUser, UserDto.class);
        //return AutoUserMapper.MAPPER.mapToUserDto(updatedUser);
    }

    @Override
    public void deleteUser(Long userId) {

        User existingUser = userRepository.findById(userId).orElseThrow(
                () -> new ResourceNotFoundException("User", "id", userId)
        );

        userRepository.deleteById(userId);
    }


	@Override
	@Async
	public void sleep() {
		long start = System.currentTimeMillis();
		log.info("sleep");
        try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        System.out.println("Sleep time in ms = " + (System.currentTimeMillis() - start));
		
	}
}
