import {Component, EventEmitter, OnInit, ViewChild} from '@angular/core';
import {HospedeService} from "../../shared/services/hospede.service";
import {HospedeModel} from "../../shared/models/hospede.model";
import {Workbook} from 'exceljs';
import saveAs from 'file-saver';
import {exportDataGrid} from 'devextreme/excel_exporter';
import {exportDataGrid as exportDataGridToPdf} from 'devextreme/pdf_exporter';
import {jsPDF} from 'jspdf';
import {DxDataGridComponent} from "devextreme-angular";

@Component({
	selector: 'app-hospede',
	templateUrl: './hospede.component.html',
	styleUrls: ['./hospede.component.scss']
})
export class HospedeComponent implements OnInit {

	@ViewChild('grid')
	grid: DxDataGridComponent;
	hospedes: HospedeModel[];
	hospedeSelecinado: HospedeModel;

	editing = {
		mode:"popup",
        allowUpdating: true,
		allowDeleting: true,
		allowAdding: true,
		onChanges: (e) => {
			// lógica após o salvamento bem-sucedido
			console.log('Dados salvos:', e.data);
		}
	};

	constructor(private hospService: HospedeService) {
		this.pegaHospedes();
		this.salvaHospede = this.salvaHospede.bind(this);
	}

	ngOnInit(): void {
	}

	salvaHospede() {
		console.log()
	}

	exportaGrid(e: any) {
		if (e.format === 'xlsx') {
			const workbook = new Workbook();
			const worksheet = workbook.addWorksheet("Main sheet");
			exportDataGrid({
				worksheet: worksheet,
				component: e.component,
			}).then(function () {
				workbook.xlsx.writeBuffer().then(function (buffer: BlobPart) {
					saveAs(new Blob([buffer], {type: "application/octet-stream"}), "DataGrid.xlsx");
				});
			});
			e.cancel = true;
		} else if (e.format === 'pdf') {
			const doc = new jsPDF();
			exportDataGridToPdf({
				jsPDFDocument: doc,
				component: e.component,
			}).then(() => {
				doc.save('DataGrid.pdf');
			});
		}
	}

	atualizaGrid(e: any) {
		this.pegaHospedes()
	}
	pegaHospedes() {
		this.hospService.getAll().subscribe(
			data => {
				this.hospedes = data
			}
		)
	}

	selecionaHospede(e) {
		e.component.byKey(e.currentSelectedRowKeys[0]).done(hospede => {
			if (hospede) {
				this.hospedeSelecinado = hospede;
			}
		});
	}

	cpfFormatado(cpf): string {
		if (cpf) {
			return cpf.replace(/(\d{3})(\d{3})(\d{3})(\d{2})/g, "$1.$2.$3-$4");
		} else {
			return cpf
		}
	}

	telefoneFormatado(telefone): string {
		if (telefone) {
			return telefone.replace(/\D/g, "").replace(/(\d{2})(\d{4})(\d{4})/g, "($1) $2-$3");
		} else {
			return telefone;
		}
	}

	celularFormatado(celular): string {
		if (celular) {
			return celular.replace(/\D/g, "").replace(/(\d{2})(\d{5})(\d{4})/g, "($1) $2-$3");
		} else {
			return celular;
		}
	}

	teste(event: any) {
		console.log(event)
	}
}
