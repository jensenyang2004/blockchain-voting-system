package contract;

import io.reactivex.Flowable;
import io.reactivex.functions.Function;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import org.web3j.abi.EventEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Bool;
import org.web3j.abi.datatypes.Event;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.RemoteFunctionCall;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.protocol.core.methods.response.BaseEventResponse;
import org.web3j.protocol.core.methods.response.Log;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tuples.generated.Tuple3;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.ContractGasProvider;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.web3j.codegen.SolidityFunctionWrapperGenerator in the 
 * <a href="https://github.com/web3j/web3j/tree/master/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version 1.4.1.
 */
@SuppressWarnings("rawtypes")
public class Election extends Contract {
    public static final String BINARY = "6080604052600080556001600260006101000a81548160ff0219169083151502179055506000600260016101000a81548160ff021916908315150217905550336002806101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555034801561008a57600080fd5b506001600360006002809054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060000160006101000a81548160ff021916908315150217905550610d91806101166000396000f3fe608060405234801561001057600080fd5b50600436106100a85760003560e01c80635ec2311e116100715780635ec2311e146103345780638da5cb5b14610376578063a13387b3146103c0578063b861c03d146103e2578063b9bc2d2b14610404578063dfbf53ae14610426576100a8565b8062b143df146100ad5780630121b93f14610154578063133a04c7146101825780633c594059146102375780635e48668f14610279575b600080fd5b6100d9600480360360208110156100c357600080fd5b81019080803590602001909291905050506104a9565b6040518080602001828103825283818151815260200191508051906020019080838360005b838110156101195780820151818401526020810190506100fe565b50505050905090810190601f1680156101465780820380516001836020036101000a031916815260200191505b509250505060405180910390f35b6101806004803603602081101561016a57600080fd5b810190808035906020019092919050505061056f565b005b6101ae6004803603602081101561019857600080fd5b810190808035906020019092919050505061068d565b6040518084815260200180602001838152602001828103825284818151815260200191508051906020019080838360005b838110156101fa5780820151818401526020810190506101df565b50505050905090810190601f1680156102275780820380516001836020036101000a031916815260200191505b5094505050505060405180910390f35b6102636004803603602081101561024d57600080fd5b810190808035906020019092919050505061075c565b6040518082815260200191505060405180910390f35b6103326004803603602081101561028f57600080fd5b81019080803590602001906401000000008111156102ac57600080fd5b8201836020820111156102be57600080fd5b803590602001918460018302840111640100000000831117156102e057600080fd5b91908080601f016020809104026020016040519081016040528093929190818152602001838380828437600081840152601f19601f8201169050808301925050505050505091929192905050506107e3565b005b6103606004803603602081101561034a57600080fd5b81019080803590602001909291905050506108f6565b6040518082815260200191505060405180910390f35b61037e610946565b604051808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200191505060405180910390f35b6103c861096b565b604051808215151515815260200191505060405180910390f35b6103ea61097e565b604051808215151515815260200191505060405180910390f35b61040c610a66565b604051808215151515815260200191505060405180910390f35b61042e610b6b565b6040518080602001828103825283818151815260200191508051906020019080838360005b8381101561046e578082015181840152602081019050610453565b50505050905090810190601f16801561049b5780820380516001836020036101000a031916815260200191505b509250505060405180910390f35b60606000600483815481106104ba57fe5b90600052602060002090600302019050806001018054600181600116156101000203166002900480601f0160208091040260200160405190810160405280929190818152602001828054600181600116156101000203166002900480156105625780601f1061053757610100808354040283529160200191610562565b820191906000526020600020905b81548152906001019060200180831161054557829003601f168201915b5050505050915050919050565b60006004828154811061057e57fe5b9060005260206000209060030201905060001515600260009054906101000a900460ff1615151415610689575b60001515600360003373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060000160009054906101000a900460ff1615151415610688576001816002015401816002018190555060008081548092919060010191905055506001600360003373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060000160006101000a81548160ff0219169083151502179055506105ab565b5b5050565b6004818154811061069a57fe5b9060005260206000209060030201600091509050806000015490806001018054600181600116156101000203166002900480601f01602080910402602001604051908101604052809291908181526020018280546001816001161561010002031660029004801561074c5780601f106107215761010080835404028352916020019161074c565b820191906000526020600020905b81548152906001019060200180831161072f57829003601f168201915b5050505050908060020154905083565b60006002809054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff16146107b757600080fd5b6000600483815481106107c657fe5b906000526020600020906003020190508060020154915050919050565b6002809054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff161461083c57600080fd5b6001600081548092919060010191905055506004604051806060016040528060015481526020018381526020016000815250908060018154018082558091505090600182039060005260206000209060030201600090919290919091506000820151816000015560208201518160010190805190602001906108bf929190610cb7565b50604082015181600201555050506002600154106108f3576001600260016101000a81548160ff0219169083151502179055505b50565b600060011515600260009054906101000a900460ff16151514156109405760006004838154811061092357fe5b906000526020600020906003020190508060020154915050610941565b5b919050565b6002809054906101000a900473ffffffffffffffffffffffffffffffffffffffff1681565b600260009054906101000a900460ff1681565b60006002809054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff1614156109f5576001600260006101000a81548160ff021916908315150217905550610a63565b6040517f08c379a000000000000000000000000000000000000000000000000000000000815260040180806020018281038252600d8152602001807f6163636573732064656e6965640000000000000000000000000000000000000081525060200191505060405180910390fd5b90565b60006002809054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff161415610afa5760011515600260019054906101000a900460ff1615151415610af5576000600260006101000a81548160ff0219169083151502179055505b610b68565b6040517f08c379a000000000000000000000000000000000000000000000000000000000815260040180806020018281038252600d8152602001807f6163636573732064656e6965640000000000000000000000000000000000000081525060200191505060405180910390fd5b90565b6060600080905060008090505b6001805403811015610bf357600060048281548110610b9357fe5b90600052602060002090600302019050600060046001840181548110610bb557fe5b90600052602060002090600302019050806002015482600201541115610bdd57829350610be4565b6001830193505b50508080600101915050610b78565b50600060048281548110610c0357fe5b90600052602060002090600302019050806001018054600181600116156101000203166002900480601f016020809104026020016040519081016040528092919081815260200182805460018160011615610100020316600290048015610cab5780601f10610c8057610100808354040283529160200191610cab565b820191906000526020600020905b815481529060010190602001808311610c8e57829003601f168201915b50505050509250505090565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f10610cf857805160ff1916838001178555610d26565b82800160010185558215610d26579182015b82811115610d25578251825591602001919060010190610d0a565b5b509050610d339190610d37565b5090565b610d5991905b80821115610d55576000816000905550600101610d3d565b5090565b9056fea265627a7a723158203c9ef9a05e90b22e61291ae854e4b288f0eaa8a3444924ac32d968292528adf764736f6c63430005100032";

    public static final String FUNC_BALLOTEND = "ballotend";

    public static final String FUNC_ENDBALLOT = "endballot";

    public static final String FUNC_FINALRESULT = "finalresult";

    public static final String FUNC_INPUT = "input";

    public static final String FUNC_NOMINEE = "nominee";

    public static final String FUNC_OWNER = "owner";

    public static final String FUNC_RESULT = "result";

    public static final String FUNC_SHOWN = "showN";

    public static final String FUNC_STARTBALLOT = "startballot";

    public static final String FUNC_VOTE = "vote";

    public static final String FUNC_WINNER = "winner";

    public static final Event VOTING_EVENT = new Event("voting", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Uint256>() {}));
    ;

    @Deprecated
    protected Election(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected Election(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected Election(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected Election(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public List<VotingEventResponse> getVotingEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(VOTING_EVENT, transactionReceipt);
        ArrayList<VotingEventResponse> responses = new ArrayList<VotingEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            VotingEventResponse typedResponse = new VotingEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.addr = (String) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.amount = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<VotingEventResponse> votingEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, VotingEventResponse>() {
            @Override
            public VotingEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(VOTING_EVENT, log);
                VotingEventResponse typedResponse = new VotingEventResponse();
                typedResponse.log = log;
                typedResponse.addr = (String) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse.amount = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<VotingEventResponse> votingEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(VOTING_EVENT));
        return votingEventFlowable(filter);
    }

    public RemoteFunctionCall<Boolean> ballotend() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_BALLOTEND, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    public RemoteFunctionCall<TransactionReceipt> endballot() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_ENDBALLOT, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<BigInteger> finalresult(BigInteger _index) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_FINALRESULT, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_index)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<TransactionReceipt> input(String name_) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_INPUT, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(name_)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<Tuple3<BigInteger, String, BigInteger>> nominee(BigInteger param0) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_NOMINEE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(param0)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Uint256>() {}));
        return new RemoteFunctionCall<Tuple3<BigInteger, String, BigInteger>>(function,
                new Callable<Tuple3<BigInteger, String, BigInteger>>() {
                    @Override
                    public Tuple3<BigInteger, String, BigInteger> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple3<BigInteger, String, BigInteger>(
                                (BigInteger) results.get(0).getValue(), 
                                (String) results.get(1).getValue(), 
                                (BigInteger) results.get(2).getValue());
                    }
                });
    }

    public RemoteFunctionCall<String> owner() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_OWNER, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<BigInteger> result(BigInteger _index) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_RESULT, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_index)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<String> showN(BigInteger _index) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_SHOWN, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_index)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<TransactionReceipt> startballot() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_STARTBALLOT, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> vote(BigInteger _index) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_VOTE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_index)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<String> winner() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_WINNER, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    @Deprecated
    public static Election load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new Election(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static Election load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new Election(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static Election load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new Election(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static Election load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new Election(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<Election> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(Election.class, web3j, credentials, contractGasProvider, BINARY, "");
    }

    public static RemoteCall<Election> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(Election.class, web3j, transactionManager, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<Election> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(Election.class, web3j, credentials, gasPrice, gasLimit, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<Election> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(Election.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "");
    }

    public static class VotingEventResponse extends BaseEventResponse {
        public String addr;

        public BigInteger amount;
    }
}
