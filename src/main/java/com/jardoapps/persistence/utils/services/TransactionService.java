package com.jardoapps.persistence.utils.services;

public interface TransactionService {

	void runAfterTransactionCommit(Runnable runnable);

	void runInSeparateTransaction(Runnable runnable);

}
