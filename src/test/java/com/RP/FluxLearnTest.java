package com.RP;

import com.RP.service.FluxLearnService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;
import reactor.util.function.Tuple2;

@SpringBootTest
public class FluxLearnTest {

    @Autowired
    private FluxLearnService fluxLearnService;

    @Test
    void testing(){
//        this.fluxLearnService.fluxLearnService();
    }

    @Test
    public void simpleFluxTest(){
        fluxLearnService.getFlux()
                .subscribe(data->{
                    System.out.println(data);
                    System.out.println("done with Flux");
                })
        ;

        fluxLearnService.fruitMix().subscribe(System.out::println);
    }

    @Test
    public void mapTest(){
//        fluxLearnService.mapExampleFlux().subscribe(System.out::println);

        //testing flux
        Flux<String> mapExampleFlux = fluxLearnService.mapExampleFlux();
        StepVerifier.create(mapExampleFlux)
                .expectNextCount(4)
                .verifyComplete();

        StepVerifier.create(mapExampleFlux)
                .expectNext("DEEPAK","NAME","RAVI","GAUTAM")
                .verifyComplete();
    }

    @Test
    public void filterExampleTest(){
        Flux<String> filterExampleFlux = fluxLearnService.filterExampleFlux();
        filterExampleFlux.subscribe(System.out::println);

        StepVerifier.create(filterExampleFlux)
                .expectNext("deepak","gautam")
                .verifyComplete();

    }

    @Test
    public void flatMapFluxTest(){
        Flux<String> stringFlux = fluxLearnService.flatMapFlux();

        stringFlux.subscribe(System.out::println);

        StepVerifier.create(stringFlux)
                .expectNextCount(20)
                .verifyComplete();

        Flux flux = fluxLearnService.transformExample();
        StepVerifier.create(flux)
                .expectNextCount(4)
                .verifyComplete();
    }

    @Test
    public void ifExampleTest(){
        Flux<String> stringFlux = fluxLearnService.ifExample(8);
        StepVerifier.create(stringFlux)
                .expectNextCount(3)
                .verifyComplete();
    }


    @Test
    public void concatExampleTest(){
        Flux<String> stringFlux = fluxLearnService.concatExample();
        stringFlux.subscribe(System.out::println);
        StepVerifier.create(stringFlux)
                .expectNextCount(7)
                .verifyComplete();
    }


    @Test
    public void concatWithExampleTest(){
        Flux<String> stringFlux = fluxLearnService.concatWithExample();
        stringFlux.subscribe(System.out::println);
        StepVerifier.create(stringFlux)
                .expectNextCount(7)
                .verifyComplete();
    }

    @Test
    public void mergeWithExampleTest(){
        Flux<String> stringFlux = fluxLearnService.mergeWithExample();

        StepVerifier.create(stringFlux)
                .expectNextCount(7)
                .verifyComplete();
    }

    @Test
    public  void zipExampleTest(){
        Flux<String> tuple2Flux = fluxLearnService.zipExample().log();

        StepVerifier.create(tuple2Flux)
                .expectNextCount(3)
                .verifyComplete();
    }

    @Test
    public void sideEffectsTest(){
        Flux<String> stringFlux = fluxLearnService.sideEffects();
        stringFlux.subscribe(System.out::println);

    }





}
