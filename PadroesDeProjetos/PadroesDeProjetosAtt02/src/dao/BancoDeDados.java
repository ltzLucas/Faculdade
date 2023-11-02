//package dao;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.List;
//
//import modelo.Armazenamento;
//import modelo.Carro;
//import modelo.Moto;
//import modelo.Veiculo;
//
//public class BancoDeDados implements Armazenamento {
//	private Connection conexao;
//	
//	public BancoDeDados(Connection conexao) {
//		this.conexao = conexao;
//	}
//
//	@Override
//    public void adicionarVeiculo(Veiculo veiculo) {
//        String sqlVeiculo = "INSERT INTO VEICULO (MARCA, MODELO, ANO_FABRICACAO, PRECO) VALUES (?, ?, ?, ?)";
//        String sqlCarro = "INSERT INTO CARRO (VEICULO_ID, NUMERO_PORTAS) VALUES (?, ?)";
//        String sqlMotocicleta = "INSERT INTO MOTOCICLETA (VEICULO_ID, CILINDRADAS) VALUES (?, ?)";
//
//        try (PreparedStatement stmtVeiculo = conexao.prepareStatement(sqlVeiculo, new String[]{"ID"});
//             PreparedStatement stmtCarro = conexao.prepareStatement(sqlCarro);
//             PreparedStatement stmtMotocicleta = conexao.prepareStatement(sqlMotocicleta)) {
//
//            conexao.setAutoCommit(false);
//
//            // Inserir informações comuns de veículo
//            stmtVeiculo.setString(1, veiculo.getMarca());
//            stmtVeiculo.setString(2, veiculo.getModelo());
//            stmtVeiculo.setInt(3, veiculo.getAno());
//            stmtVeiculo.setDouble(4, veiculo.getPreco());
//
//            // Executar a inserção e obter o ID gerado
//            int affectedRows = stmtVeiculo.executeUpdate();
//            if (affectedRows == 0) {
//                throw new SQLException("A inserção do veículo falhou, nenhum registro afetado.");
//            }
//
//            try (var generatedKeys = stmtVeiculo.getGeneratedKeys()) {
//                if (generatedKeys.next()) {
//                    long veiculoId = generatedKeys.getLong(1);
//
//                    // Inserir informações específicas de carro ou moto
//                    if (veiculo instanceof Carro) {
//                        stmtCarro.setLong(1, veiculoId);
//                        stmtCarro.setInt(2, ((Carro) veiculo).getNumeroPortas());
//                        stmtCarro.executeUpdate();
//                    } else if (veiculo instanceof Moto) {
//                        stmtMotocicleta.setLong(1, veiculoId);
//                        stmtMotocicleta.setInt(2, ((Moto) veiculo).getCilindradas());
//                        stmtMotocicleta.executeUpdate();
//                    }
//                } else {
//                    throw new SQLException("A inserção do veículo falhou, nenhum ID gerado.");
//                }
//            }
//
//            conexao.commit();
//        } catch (SQLException e) {
//            try {
//                if (conexao != null) {
//                    conexao.rollback();
//                }
//            } catch (SQLException ex) {
//                ex.printStackTrace();
//            }
//            e.printStackTrace();
//        } finally {
//            try {
//                if (conexao != null) {
//                    conexao.setAutoCommit(true);
//                }
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//
//	 @Override
//	    public List<Veiculo> recuperarVeiculos() {
//	        List<Veiculo> veiculos = new ArrayList<>();
//
//	        String sql = "SELECT V.ID, V.MARCA, V.MODELO, V.ANO_FABRICACAO, V.PRECO, C.NUMERO_PORTAS, M.CILINDRADAS " +
//	                     "FROM VEICULO V " +
//	                     "LEFT JOIN CARRO C ON V.ID = C.VEICULO_ID " +
//	                     "LEFT JOIN MOTOCICLETA M ON V.ID = M.VEICULO_ID";
//
//	        try (PreparedStatement stmt = conexao.prepareStatement(sql);
//	             ResultSet rs = stmt.executeQuery()) {
//
//	            while (rs.next()) {
//	                long id = rs.getLong("ID");
//	                String marca = rs.getString("MARCA");
//	                String modelo = rs.getString("MODELO");
//	                int anoFabricacao = rs.getInt("ANO_FABRICACAO");
//	                double preco = rs.getDouble("PRECO");
//
//	                if (rs.getObject("NUMERO_PORTAS") != null) {
//	                    // É um carro
//	                    int numeroPortas = rs.getInt("NUMERO_PORTAS");
//	                    Veiculo carro = new Carro(marca, modelo, anoFabricacao, preco, numeroPortas);
//	                    
//	                    veiculos.add(carro);
//	                } else if (rs.getObject("CILINDRADAS") != null) {
//	                    // É uma motocicleta
//	                    int cilindradas = rs.getInt("CILINDRADAS");
//	                    Veiculo motocicleta = new Moto(marca, modelo, anoFabricacao, preco, cilindradas);
//	                    veiculos.add(motocicleta);
//	                }
//	            }
//	        } catch (SQLException e) {
//	            e.printStackTrace();
//	        }
//
//	        return veiculos;
//	    }
//
//}
