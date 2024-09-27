package vn.edu.ptit.supermarket.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import vn.edu.ptit.supermarket.core_authentication.model.response.BaseResponse;
import vn.edu.ptit.supermarket.core_kafka.producer.MessageProducer;
import vn.edu.ptit.supermarket.service.TestService;

@Slf4j
@RestController
@RequestMapping("/api/v1/test")
@RequiredArgsConstructor
@Tag(name = "Test", description = "Test API")
public class TestController {

  private final MessageProducer messageProducer;
  private final TestService testService;

  @GetMapping()
  @Operation(summary = "Test", description = "Test API")
  public BaseResponse<String> test(
      @Parameter(name = "name", description = "Name", required = true)
      @Valid @RequestParam String name) throws InterruptedException {
    log.info("(test)name: {}", name);
    messageProducer.sendMessage("supermarket", name);
    for(int i=0; i<10; i++) {
      Thread.sleep(2000);
      log.info("{}", i);
    }
    return BaseResponse.of(HttpStatus.OK.value(), LocalDateTime.now().toString(), testService.testRedis(name));
  }
}
