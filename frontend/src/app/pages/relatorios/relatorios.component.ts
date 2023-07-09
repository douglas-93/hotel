import {Component, OnInit, ViewChild} from '@angular/core';
import {DxSelectBoxComponent} from "devextreme-angular";
import notify from "devextreme/ui/notify";
import {RelatoriosService} from "../../shared/services/relatorios.service";

@Component({
  selector: 'app-relatorios',
  templateUrl: './relatorios.component.html',
  styleUrls: ['./relatorios.component.scss']
})
export class RelatoriosComponent implements OnInit {

	@ViewChild('modeloRelatorio') modeloRelatorio: DxSelectBoxComponent;

	modelosRelatorios: string[] = ['Hospedes'];
	loadingVisible: boolean = false;

  constructor(private relatorioService: RelatoriosService) { }

  ngOnInit(): void {
  }

	gerar() {
		let mod = this.modeloRelatorio.selectedItem

		if (mod === null) {
			notify('Selecione o modelo', 'error', 3000)
			return
		}

		if (mod === 'Hospedes') {
			this.loadingVisible = true
			this.relatorioService.relatorioHospedes().subscribe(response => {
				const file = new Blob([response.body!], { type: 'application/pdf' });
				const fileURL = URL.createObjectURL(file);
				window.open(fileURL, '_blank');
				this.loadingVisible = false
			});
		}
	}
}
