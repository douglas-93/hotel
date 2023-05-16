import {Component, OnInit} from '@angular/core';
import {HospedeService} from "../../shared/services/hospede.service";
import {HospedeModel} from "../../shared/models/hospede.model";
// @ts-ignore
import {Workbook} from 'exceljs';
import saveAs from 'file-saver';
import {exportDataGrid} from 'devextreme/excel_exporter';
import {exportDataGrid as exportDataGridToPdf} from 'devextreme/pdf_exporter';
import {jsPDF} from 'jspdf';
import {DxoButtonOptions} from "devextreme-angular/ui/nested/base/button-options";

@Component({
  selector: 'app-hospede',
  templateUrl: './hospede.component.html',
  styleUrls: ['./hospede.component.scss']
})
export class HospedeComponent implements OnInit {

  hospedes: HospedeModel[];
  hospedeSelecinado: HospedeModel;
  popup: boolean = false;
  novoHospede: HospedeModel;

  constructor(private hospService: HospedeService) {
    this.pegaHospedes();
  }

  ngOnInit(): void {
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

  adicionaHospede(e) {
    this.popup = true
  }

  selecionaHospede(e) {
    e.component.byKey(e.currentSelectedRowKeys[0]).done(hospede => {
      if (hospede) {
        this.hospedeSelecinado = hospede;
      }
    });
  }

  editaHospede(e) {

  }

  deletaHospede(e) {

  }

  closePopup() {
    this.popup = false;
  }

  salvarHospede() {
    console.log(this.novoHospede)
  }
}
