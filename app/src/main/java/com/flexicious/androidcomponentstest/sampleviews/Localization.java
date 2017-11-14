////
////  iPadLocalizationViewController.m
////  IOSComponentsSample
////
////  Created by Flexicious-110 on 7/17/13.
////  Copyright (c) 2013 ___IOSComponents___. All rights reserved.
////
package com.flexicious.androidcomponentstest.sampleviews;

import android.os.Bundle;

import com.flexicious.androidcomponentstest.R;
import com.flexicious.example.model.classic.Employee;
import com.flexicious.grids.filters.Filter;
import com.flexicious.nestedtreedatagrid.FlexDataGridColumn;
import com.flexicious.nestedtreedatagrid.events.FlexDataGridEvent;
import com.flexicious.utils.Constants;

public class Localization extends ExampleActivityBase {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.example_localization);
		this.buildGrid(this.flexDataGrid, R.raw.flxslocalization);
		this.flexDataGrid.setDataProvider(Employee.getAllEmployees());
	}

	public String CustomMatchFilterControl_getFullName(Employee item,
			FlexDataGridColumn col) {
		return item.firstName + " " + item.lastName;
	}

	public void localization_creationCompleteHandler(FlexDataGridEvent ns) {
		// These would go into a global init file or something
		// todo sal

		Filter.ALL_ITEM = "tous";
		Constants.MCS_LBL_TITLE_TEXT = "Trier la colonne multi";
		Constants.MCS_LBL_HEADER_TEXT = "S'il vous plaît spécifier l'ordre de tri et de la direction des colonnes que vous souhaitez trier par:";
		Constants.MCS_LBL_SORT_BY_TEXT = "Trier par:";
		// Constants.MCS_LBL_THEN_BY_TEXT ="Then par:";
		Constants.MCS_RBN_ASCENDING_LABEL = "ascendant";
		Constants.MCS_RBN_DESCENDING_LABEL = "descendant";
		Constants.MCS_BTN_CLEAR_ALL_LABEL = "effacer tout";
		Constants.MCS_BTN_APPLY_LABEL = "appliquer";
		Constants.MCS_BTN_CANCEL_LABEL = "annuler";

		Constants.SAVESETTINGS_LBL_TEXT_TITLE = "Colonnes à afficher";
		Constants.SAVESETTINGS_LBL_TEXT_TITLE = "Les préférences que vous définissez ci-dessous sont conservées lors de cette grille est chargé dans l'avenir:";
		Constants.SAVESETTINGS_CBX_ORDER_OF_COLUMNS = "Nom de l'option:";
		Constants.SAVESETTINGS_CBX_FILTER_CRITERIA = "Ordre des colonnes";
		Constants.SAVESETTINGS_CBX_VISIBILITY_OF_COLUMNS = "Visibilité des colonnes";
		Constants.SAVESETTINGS_CBX_WIDTHS_OF_COLUMNS = "Largeurs de colonnes";
		Constants.SAVESETTINGS_CBX_FILTER_CRITERIA = "Critères de filtrage";
		Constants.SAVESETTINGS_CBX_SORT_SETTINGS = "Réglages Trier";
		Constants.SAVESETTINGS_CBX_SCROLL_POSITIONS = "positions de défilement";
		Constants.SAVESETTINGS_CBX_FILTER_AND_FOOTERVISIBILITY = "Visiblité filtre et pied de page";
		Constants.SAVESETTINGS_CBX_RECORDS_PER_PAGE = "Enregistrements par page";
		Constants.SAVESETTINGS_CBX_PRINT_SETTINGS = "Paramètres d'impression";
		Constants.SAVESETTINGS_BTN_CLEAR_SAVED_PREFERENCES = "Supprimer toutes les préférences sauvegardées";
		Constants.SAVESETTINGS_BTN_SAVE_PREFERENCES = "Claires Préférences sauvegardées";
		Constants.SAVESETTINGS_BTN_SAVE_PREFERENCES = "Enregistrer les préférences";
		Constants.SAVESETTINGS_BTN_CANCEL = "annuler";

		Constants.SETTINGS_LBL_TEXT_TITLE = "Colonnes à afficher";
		Constants.SETTINGS_CBX_SHOW_FOOTERS = "montrer pieds de page";
		Constants.SETTINGS_CBX_SHOW_FILTER = "Filtrer";
		Constants.SETTINGS_LBL_RECORDS_PER_PAGE = "Enregistrements par page";
		Constants.SETTINGS_BTN_APPLY = "Appliquer";
		Constants.SETTINGS_BTN_CANCEL = "Annuler";
 

		Constants.PGR_BTN_WORD_TOOLTIP = "Exporter vers Word";
		Constants.PGR_BTN_EXCEL_TOOLTIP = "Exporter vers Excel";
		Constants.PGR_BTN_PDF_TOOLTIP = "Imprimer au format PDF";
		Constants.PGR_BTN_PRINT_TOOLTIP = "imprimer";
		Constants.PGR_BTN_CLEAR_FILTER_TOOLTIP = "Effacer le filtre";
		Constants.PGR_BTN_RUN_FILTER_TOOLTIP = "Exécuter Filtrer";
		Constants.PGR_BTN_FILTER_TOOLTIP = "Afficher / Masquer filtre";
		Constants.PGR_BTN_FOOTER_TOOLTIP = "Afficher / Masquer Footer";
		Constants.PGR_BTN_SAVE_PREFS_TOOLTIP = "Enregistrer les préférences";
		Constants.PGR_BTN_PREFERENCES_TOOLTIP = "préférences";
		Constants.PGR_BTN_COLLAPSE_ALL_TOOLTIP = "Réduire tout";
		Constants.PGR_BTN_EXP_ALL_TOOLTIP = "Développer tout";
		Constants.PGR_BTN_EXP_ONE_UP_TOOLTIP = "Développer un Level Up";
		Constants.PGR_BTN_EXP_ONE_DOWN_TOOLTIP = "Développer un niveau plus bas";
		Constants.PGR_BTN_MCS_TOOLTIP = "Tri sur plusieurs colonnes";

		Constants.PGR_BTN_FIRST_PAGE_TOOLTIP = "Première page";
		Constants.PGR_BTN_PREV_PAGE_TOOLTIP = "page précédente";
		Constants.PGR_BTN_NEXT_PAGE_TOOLTIP = "page suivante";
		Constants.PGR_BTN_LAST_PAGE_TOOLTIP = "Dernière page";
		Constants.PGR_LBL_GOTO_PAGE_TEXT = "Aller à la page:";

		Constants.PGR_ITEMS = "Articles";
		Constants.PGR_TO = "à";
		Constants.PGR_OF = "de";
		Constants.PGR_PAGE = "page";

		Constants.SELECTED_RECORDS = "enregistrements sélectionnés";
		Constants.NONE_SELECTED = "Aucune sélection";

		Constants.EXP_LBL_TITLE_TEXT = "options d'exportation";
		Constants.EXP_RBN_CURRENT_PAGE_LABEL = "page courante";
		Constants.EXP_RBN_ALL_PAGES_LABEL = "toutes les pages";
		Constants.EXP_RBN_SELECT_PGS_LABEL = "spécifiez Pages";
		Constants.EXP_LBL_EXPORT_FORMAT_TEXT = "Export au format:";
		Constants.EXP_LBL_COLS_TO_EXPORT_TEXT = "Colonnes à exporter:";
		Constants.EXP_BTN_EXPORT_LABEL = "exporter";
		Constants.EXP_BTN_CANCEL_LABEL = "annuler";

		Constants.PPRV_LBL_TITLE_TEXT = "options d'impression";
		Constants.PRT_LBL_TITLE_TEXT = "options d'impression";
		Constants.PRT_LBL_PRT_OPTIONS_TEXT = "options d'impression:";
		Constants.PRT_RBN_CURRENT_PAGE_LABEL = "page courante";
		Constants.PRT_RBN_ALL_PAGES_LABEL = "toutes les pages";
		Constants.PRT_RBN_SELECT_PGS_LABEL = "spécifiez Pages";
		Constants.PRT_CB_PRVW_PRINT_LABEL = "Aperçu avant impression";
		Constants.PRT_LBL_COLS_TO_PRINT_TEXT = "Colonnes à imprimer:";
		Constants.PRT_BTN_PRINT_LABEL = "imprimer";
		Constants.PRT_BTN_CANCEL_LABEL = "annuler";

		Constants.PPRV_LBL_PG_SIZE_TEXT = "Taille de la page:";
		Constants.PPRV_LBL_LAYOUT_TEXT = "Mise en page:";
		Constants.PPRV_LBL_COLS_TEXT = "colonnes:";
		Constants.PPRV_CB_PAGE_HDR_LABEL = "tête de page";
		Constants.PPRV_CB_PAGE_FTR_LABEL = "Pied de page";
		Constants.PPRV_CB_RPT_FTR_LABEL = "tête de l'état";
		Constants.PPRV_CB_RPT_HDR_LABEL = "Rapport pied de page";
		Constants.PPRV_BTN_PRT_LABEL = "imprimer";
		Constants.PPRV_BTN_CANCEL_LABEL = "annuler";
		Constants.PPRV_LBL_SETTINGS_1_TEXT = "Remarque: Modification de la taille de page ou mise en page ne ​​mettra à jour l'aperçu, pas la réelle impression.";
		Constants.PPRV_LBL_SETTINGS_2_TEXT = "S'il vous plaît régler la Taille de la page / Mise en page sur Paramètres de l'imprimante via la boîte de dialogue Imprimer qui sera affiché lorsque vous imprimez.";
		Constants.PPRV_BTN_PRT_1_LABEL = "imprimer";
		Constants.PPRV_BTN_CANCEL_1_LABEL = "annuler";
	}

}
