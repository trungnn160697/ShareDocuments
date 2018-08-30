package com.rabiloo.sharedocument.controller.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rabiloo.sharedocument.dto.GraphDto;
import com.rabiloo.sharedocument.service.DocumentService;

@RestController
public class GraphResource {
	@Autowired
	private DocumentService documentService;

	@GetMapping("/graph")
	public ResponseEntity<GraphDto> graph() {
		GraphDto graph = new GraphDto();
		graph.setNumberOfDownloadMath(documentService.countNumberOfDownloadBySubject(1));
		graph.setNumberOfDownLoadPhysical(documentService.countNumberOfDownloadBySubject(3));
		graph.setNumberOfDownLoadChemistry(documentService.countNumberOfDownloadBySubject(2));
		return new ResponseEntity<GraphDto>(graph, HttpStatus.OK);
	}
}
