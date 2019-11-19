package com.jardoapps.persistence.utils.services.impl;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronization;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import com.jardoapps.persistence.utils.services.TransactionService;

public class TransactionServiceImpl implements TransactionService {

	@Override
	public void runAfterTransactionCommit(Runnable runnable) {
		TransactionSynchronizationManager.registerSynchronization(new RunnableCaller(runnable));
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void runInSeparateTransaction(Runnable runnable) {
		runnable.run();
	}

	private class RunnableCaller implements TransactionSynchronization {

		private final Runnable runnable;

		public RunnableCaller(Runnable runnable) {
			this.runnable = runnable;
		}

		@Override
		public void afterCommit() {
			runnable.run();
		}

		@Override
		public void suspend() {
			// do nothing
		}

		@Override
		public void resume() {
			// do nothing
		}

		@Override
		public void flush() {
			// do nothing
		}

		@Override
		public void beforeCommit(boolean readOnly) {
			// do nothing
		}

		@Override
		public void beforeCompletion() {
			// do nothing
		}

		@Override
		public void afterCompletion(int status) {
			// do nothing
		}

	}

}
