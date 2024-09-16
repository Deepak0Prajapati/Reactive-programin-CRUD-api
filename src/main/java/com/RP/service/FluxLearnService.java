package com.RP.service;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.util.function.Tuple2;

import java.util.List;
import java.util.function.Function;

@Service
public class FluxLearnService {

    public void fluxLearnService(){
        System.out.println("Testing web flux learn service!!");
    }

    //creating flux

    public Flux<String> getFlux(){
        Flux<String> flux = Flux.just("deepak", "name", "ravi", "gautam").log();
        return flux;

    }

    public Flux<String> fruitMix(){
        List<String> fruitsName=List.of("apple","banana","orange");
        return Flux.fromIterable(fruitsName);
    }

    public Flux<Void> emptyFlux(){
        return Flux.empty();
    }

    public Flux<String> mapExampleFlux(){
//        Flux<String> stringFlux = getFlux().map(name -> name.toUpperCase());
        Flux<String> stringFlux = getFlux().map(String::toUpperCase);//more easy way
        return stringFlux;

    }

    //filter

    public Flux<String> filterExampleFlux(){
        return getFlux().filter(name ->name.length()>4);
    }

    public Flux<String> flatMapFlux(){
        return getFlux().flatMap(name-> Flux.just(name.split("")));
    }

    //transform example

    Function<Flux<String>,Flux<String>> fluxFunction=(name)->name.map(String::toUpperCase);
    public Flux transformExample(){
        Flux<String> transformed = getFlux().transform(fluxFunction);
        return  transformed;
    }


    public Flux<String> ifExample(int length){
        return getFlux()
                .filter(name->name.length()>length)
//                .defaultIfEmpty("default value!!")
                .switchIfEmpty(fruitMix())
                ;

    }

    public Flux<String> concatExample(){
        return Flux.concat(getFlux(),fruitMix());
    }

    public Flux<String> concatWithExample(){
        return getFlux().concatWith(fruitMix());
    }
    public Flux<String> mergeWithExample(){

//       return getFlux().mergeWith(fruitMix());

        return Flux.merge(getFlux(),fruitMix());
    }


    //zip and zipwith example
    public Flux<String> zipExample(){
       return Flux.zip(getFlux(),Flux.just(12,23,44),(first,second)->{
           return first+" : "+second;
               }
               );
    }

    //to do certain task before a on next

    public Flux<String> sideEffects(){
          return   getFlux().doOnNext(data->{
                System.out.println(data+" : on next");
            })
                  .doOnSubscribe(data->{
                      System.out.println(data+" : on subscribe");
                  })
                  .doOnEach(data->{
                      System.out.println(data+ " : on each");
                  })
                  .doOnComplete(()->{
                      System.out.println(" on complete");
                  });

    }




}
