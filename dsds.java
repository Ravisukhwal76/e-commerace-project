   @GetMapping("/api/getUsers/{id}")
   public ResponseEntity<List<UserDto>> getUsersExcludingCurrent(@PathVariable("id") Integer id) {
       try {
           log.info("Received request to fetch users excluding id: {}", id);

           List<User> users = userService.getUsersExcludingCurrent(id);

           // Filter users based on ROLE_EMPLOYEE
           List<User> employeeUsers = users.stream()
                   .filter(user -> user.getRoles().stream()
                           .anyMatch(role -> role.getName() == ERole.ROLE_EMPLOYEE))
                   .collect(Collectors.toList());

            List<UserDto> userDtos = userService.convertToDtoList(employeeUsers);

           log.info("Returning {} employee users in the response.", userDtos.size());
           return new ResponseEntity<>(userDtos, HttpStatus.OK);
       } catch (Exception e) {
           log.error("Error processing getUsersExcludingCurrent request.", e);
           return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
       }
   }
