package com.itechtopus.sshgenerator.sheduler;

import com.itechtopus.sshgenerator.generator.AllInfoGenerator;
import com.itechtopus.sshgenerator.storage.MainStorage;
import com.itechtopus.sshgenerator.storage.Parameters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

public class GeneratorShceduler implements Runnable{

  private final Logger log = LoggerFactory.getLogger(getClass());

  private long counter = 0;

  private AllInfoGenerator generator;

  public GeneratorShceduler() {
    generator = AllInfoGenerator.get();
  }

  @Override
  public void run() {
    log.info("Scheduler started");
    while (!Thread.currentThread().isInterrupted()) {
      try {
        Thread.sleep(Parameters.SCHEDULER_PERIOD);
        doIteration();
      } catch (InterruptedException e) {
        log.info("Interrupted from outside. Counter parameter:{}", counter);
        return;
      }
    }
  }

  private void doIteration() {
    doCount();
    generateTransactions();
    if (counter %  Parameters.ACCOUNT_GENERATION_PERIOD == 0)
      generateAccounts();
    if (counter % Parameters.CLIENT_GENERATION_PERIOD == 0)
      generateClient();
  }

  private void doCount() {
    if ((counter++)+100 > Long.MAX_VALUE)
      counter = 0;
  }

  private void generateClient() {
    log.info("Generating new client: " + generator.generateNewClientPI());
  }

  private void generateAccounts() {
    log.info("Generating new Account: " + generator.generateNewAccount());
    log.info("Transactions generated:" +
        Arrays
            .stream(MainStorage.operations.toString()
                .split("}"))
                .filter(line -> !line.contains("new_account"))
                .count());
  }

  private void generateTransactions() {
    for (int i = 0; i < Parameters.TRANSACTIONS_PER_ITERATION; i++)
      generator.generateNewTransaction();
  }



}
