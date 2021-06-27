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
import org.web3j.abi.FunctionEncoder;
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
public class Editedelection extends Contract {
    public static final String BINARY = "60806040526000805560006001556001600260006101000a81548160ff0219169083151502179055506000600260016101000a81548160ff02191690831515021790555034801561004f57600080fd5b506040516110433803806110438339818101604052602081101561007257600080fd5b81019080805190602001909291905050506001600360006002809054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060000160006101000a81548160ff0219169083151502179055507fcd34be789d9568ee991846026f66af161b9d7cf6f7307d3d0778b843cd33dcb230604051808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200191505060405180910390a1806002806101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555050610e91806101b26000396000f3fe608060405234801561001057600080fd5b50600436106100a85760003560e01c80635ec2311e116100715780635ec2311e146103345780638da5cb5b14610376578063a13387b3146103c0578063b861c03d146103e2578063b9bc2d2b14610404578063dfbf53ae14610426576100a8565b8062b143df146100ad5780630121b93f14610154578063133a04c7146101825780633c594059146102375780635e48668f14610279575b600080fd5b6100d9600480360360208110156100c357600080fd5b81019080803590602001909291905050506104a9565b6040518080602001828103825283818151815260200191508051906020019080838360005b838110156101195780820151818401526020810190506100fe565b50505050905090810190601f1680156101465780820380516001836020036101000a031916815260200191505b509250505060405180910390f35b6101806004803603602081101561016a57600080fd5b810190808035906020019092919050505061056f565b005b6101ae6004803603602081101561019857600080fd5b81019080803590602001909291905050506106f8565b6040518084815260200180602001838152602001828103825284818151815260200191508051906020019080838360005b838110156101fa5780820151818401526020810190506101df565b50505050905090810190601f1680156102275780820380516001836020036101000a031916815260200191505b5094505050505060405180910390f35b6102636004803603602081101561024d57600080fd5b81019080803590602001909291905050506107c7565b6040518082815260200191505060405180910390f35b6103326004803603602081101561028f57600080fd5b81019080803590602001906401000000008111156102ac57600080fd5b8201836020820111156102be57600080fd5b803590602001918460018302840111640100000000831117156102e057600080fd5b91908080601f016020809104026020016040519081016040528093929190818152602001838380828437600081840152601f19601f82011690508083019250505050505050919291929050505061084e565b005b6103606004803603602081101561034a57600080fd5b8101908080359060200190929190505050610961565b6040518082815260200191505060405180910390f35b61037e6109b1565b604051808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200191505060405180910390f35b6103c86109d6565b604051808215151515815260200191505060405180910390f35b6103ea6109e9565b604051808215151515815260200191505060405180910390f35b61040c610b2a565b604051808215151515815260200191505060405180910390f35b61042e610c88565b6040518080602001828103825283818151815260200191508051906020019080838360005b8381101561046e578082015181840152602081019050610453565b50505050905090810190601f16801561049b5780820380516001836020036101000a031916815260200191505b509250505060405180910390f35b60606000600483815481106104ba57fe5b90600052602060002090600302019050806001018054600181600116156101000203166002900480601f0160208091040260200160405190810160405280929190818152602001828054600181600116156101000203166002900480156105625780601f1061053757610100808354040283529160200191610562565b820191906000526020600020905b81548152906001019060200180831161054557829003601f168201915b5050505050915050919050565b60006004828154811061057e57fe5b9060005260206000209060030201905060001515600260009054906101000a900460ff16151514156106f4575b60001515600360003373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060000160009054906101000a900460ff16151514156106f3576001816002015401816002018190555060008081548092919060010191905055506001600360003373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060000160006101000a81548160ff0219169083151502179055507f2f558a8fb0884eca50dbb9872cd24387f042fed01e3c84fdd6a618848262cba53383604051808373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020018281526020019250505060405180910390a16105ab565b5b5050565b6004818154811061070557fe5b9060005260206000209060030201600091509050806000015490806001018054600181600116156101000203166002900480601f0160208091040260200160405190810160405280929190818152602001828054600181600116156101000203166002900480156107b75780601f1061078c576101008083540402835291602001916107b7565b820191906000526020600020905b81548152906001019060200180831161079a57829003601f168201915b5050505050908060020154905083565b60006002809054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff161461082257600080fd5b60006004838154811061083157fe5b906000526020600020906003020190508060020154915050919050565b6002809054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff16146108a757600080fd5b60016000815480929190600101919050555060046040518060600160405280600154815260200183815260200160008152509080600181540180825580915050906001820390600052602060002090600302016000909192909190915060008201518160000155602082015181600101908051906020019061092a929190610db7565b506040820151816002015550505060026001541061095e576001600260016101000a81548160ff0219169083151502179055505b50565b600060011515600260009054906101000a900460ff16151514156109ab5760006004838154811061098e57fe5b9060005260206000209060030201905080600201549150506109ac565b5b919050565b6002809054906101000a900473ffffffffffffffffffffffffffffffffffffffff1681565b600260009054906101000a900460ff1681565b60006002809054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff1614610a4457600080fd5b6002809054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff161415610ab9576001600260006101000a81548160ff021916908315150217905550610b27565b6040517f08c379a000000000000000000000000000000000000000000000000000000000815260040180806020018281038252600d8152602001807f6163636573732064656e6965640000000000000000000000000000000000000081525060200191505060405180910390fd5b90565b60006002809054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff1614610b8557600080fd5b6002809054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff161415610c175760011515600260019054906101000a900460ff1615151415610c12576000600260006101000a81548160ff0219169083151502179055505b610c85565b6040517f08c379a000000000000000000000000000000000000000000000000000000000815260040180806020018281038252600d8152602001807f6163636573732064656e6965640000000000000000000000000000000000000081525060200191505060405180910390fd5b90565b60606000809050600080905060008090505b6001805403811015610cf257600060048281548110610cb557fe5b906000526020600020906003020190508060020154831115610ce05780600201549250819350610ce4565b8292505b508080600101915050610c9a565b50600060048381548110610d0257fe5b90600052602060002090600302019050806001018054600181600116156101000203166002900480601f016020809104026020016040519081016040528092919081815260200182805460018160011615610100020316600290048015610daa5780601f10610d7f57610100808354040283529160200191610daa565b820191906000526020600020905b815481529060010190602001808311610d8d57829003601f168201915b5050505050935050505090565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f10610df857805160ff1916838001178555610e26565b82800160010185558215610e26579182015b82811115610e25578251825591602001919060010190610e0a565b5b509050610e339190610e37565b5090565b610e5991905b80821115610e55576000816000905550600101610e3d565b5090565b9056fea265627a7a7231582030ff45bb1ebddc475ee546a511fb6c22e47f5eaec518abbf953a109456c93e3564736f6c63430005110032";
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

    public static final Event CONTRACT_ADDRESS_EVENT = new Event("contract_address", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
    ;

    public static final Event VOTING_EVENT = new Event("voting", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Uint256>() {}));
    ;

    @Deprecated
    protected Editedelection(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected Editedelection(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected Editedelection(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected Editedelection(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public List<Contract_addressEventResponse> getContract_addressEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(CONTRACT_ADDRESS_EVENT, transactionReceipt);
        ArrayList<Contract_addressEventResponse> responses = new ArrayList<Contract_addressEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            Contract_addressEventResponse typedResponse = new Contract_addressEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.CONTRACT_ADDRESS = (String) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<Contract_addressEventResponse> contract_addressEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, Contract_addressEventResponse>() {
            @Override
            public Contract_addressEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(CONTRACT_ADDRESS_EVENT, log);
                Contract_addressEventResponse typedResponse = new Contract_addressEventResponse();
                typedResponse.log = log;
                typedResponse.CONTRACT_ADDRESS = (String) eventValues.getNonIndexedValues().get(0).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<Contract_addressEventResponse> contract_addressEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(CONTRACT_ADDRESS_EVENT));
        return contract_addressEventFlowable(filter);
    }

    public List<VotingEventResponse> getVotingEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(VOTING_EVENT, transactionReceipt);
        ArrayList<VotingEventResponse> responses = new ArrayList<VotingEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            VotingEventResponse typedResponse = new VotingEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.addr = (String) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.name = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
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
                typedResponse.name = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
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
    public static Editedelection load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new Editedelection(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static Editedelection load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new Editedelection(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static Editedelection load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new Editedelection(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static Editedelection load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new Editedelection(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<Editedelection> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider, String _owner) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, _owner)));
        return deployRemoteCall(Editedelection.class, web3j, credentials, contractGasProvider, BINARY, encodedConstructor);
    }

    public static RemoteCall<Editedelection> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider, String _owner) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, _owner)));
        return deployRemoteCall(Editedelection.class, web3j, transactionManager, contractGasProvider, BINARY, encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<Editedelection> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit, String _owner) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, _owner)));
        return deployRemoteCall(Editedelection.class, web3j, credentials, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<Editedelection> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit, String _owner) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, _owner)));
        return deployRemoteCall(Editedelection.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    public static class Contract_addressEventResponse extends BaseEventResponse {
        public String CONTRACT_ADDRESS;
    }

    public static class VotingEventResponse extends BaseEventResponse {
        public String addr;

        public BigInteger name;
    }
}
