/**
 * Copyright 2014-2016 yangming.liu<liuyangming@gmail.com>.
 *
 * This copyrighted material is made available to anyone wishing to use, modify,
 * copy, or redistribute it subject to the terms and conditions of the GNU
 * Lesser General Public License, as published by the Free Software Foundation.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY
 * or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU Lesser General Public License
 * for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this distribution; if not, see <http://www.gnu.org/licenses/>.
 */
package org.bytesoft.transaction;

import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;

import org.bytesoft.transaction.archive.TransactionArchive;
import org.bytesoft.transaction.supports.TransactionListener;
import org.bytesoft.transaction.supports.TransactionResourceListener;
import org.bytesoft.transaction.supports.resource.XAResourceDescriptor;

public interface Transaction extends javax.transaction.Transaction {

	public boolean isLocalTransaction();

	public void setRollbackOnlyQuietly();

	public int getTransactionStatus();

	public void setTransactionStatus(int status);

	public void resume() throws SystemException;

	public void suspend() throws SystemException;

	public boolean isTiming();

	public void setTransactionTimeout(int seconds);

	public void registerTransactionListener(TransactionListener listener);

	public void registerTransactionResourceListener(TransactionResourceListener listener);

	public Object getTransactionalExtra();

	public void setTransactionalExtra(Object transactionalExtra);

	public XAResourceDescriptor getResourceDescriptor(String identifier);

	public XAResourceDescriptor getRemoteCoordinator(String application);

	public TransactionContext getTransactionContext();

	public TransactionArchive getTransactionArchive();

	public int participantPrepare() throws RollbackRequiredException, CommitRequiredException;

	public void participantCommit(boolean opc) throws RollbackException, HeuristicMixedException, HeuristicRollbackException,
			SecurityException, IllegalStateException, CommitRequiredException, SystemException;

	public void participantRollback() throws IllegalStateException, RollbackRequiredException, SystemException;

	public void forget() throws SystemException;

	public void forgetQuietly();

	public void recover() throws SystemException;

	public void recoveryCommit() throws CommitRequiredException, SystemException;

	public void recoveryRollback() throws RollbackRequiredException, SystemException;

	public Exception getCreatedAt();

}
