export class EstadoModel {
	ufId: number;
	ufSigla: string;
	ufNome: string;
	regiaoId: number;
	regiaoSigla: string;
	regiaoNome: string;

	converteResposta(data): EstadoModel {
		this.ufId = data['UF-id']
		this.ufSigla = data['UF-sigla']
		this.ufNome = data['UF-nome']
		this.regiaoId = data['regiao-id']
		this.regiaoSigla = data['regiao-sigla']
		this.regiaoNome = data['regiao-nome']
		return this
	}
}
