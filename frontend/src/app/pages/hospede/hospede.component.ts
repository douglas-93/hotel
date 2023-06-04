import {Component, ViewChild} from '@angular/core';
import {HospedeService} from "../../shared/services/hospede.service";
import {HospedeModel} from "../../shared/models/hospede.model";
import {Workbook} from 'exceljs';
import saveAs from 'file-saver';
import {exportDataGrid} from 'devextreme/excel_exporter';
import {exportDataGrid as exportDataGridToPdf} from 'devextreme/pdf_exporter';
import {jsPDF} from 'jspdf';
import {DxDataGridComponent} from "devextreme-angular";
import {Router} from "@angular/router";

@Component({
	selector: 'app-hospede',
	templateUrl: './hospede.component.html',
	styleUrls: ['./hospede.component.scss']
})
export class HospedeComponent {

	@ViewChild('grid')
	grid: DxDataGridComponent;
	hospedes: HospedeModel[];
	hospedeSelecinado: HospedeModel;
	loadingVisible: boolean = false;


	constructor(private hospService: HospedeService,
				private router: Router) {
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

	buscaHospedes() {
		this.loadingVisible = true;
		this.hospService.getHospedes().subscribe(
			data => {
				this.hospedes = data
				this.loadingVisible = false;
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

	direcionaForm() {
		this.router.navigate(['pages', 'hospedes', 'cad'])
	}

	editar(e: MouseEvent) {
		this.router.navigate(['pages', 'hospedes', 'edit', this.hospedeSelecinado.id])
	}
}
